package kg.bektur.hippodromesweepstakes.services;

import jakarta.annotation.PostConstruct;
import kg.bektur.hippodromesweepstakes.entities.*;
import kg.bektur.hippodromesweepstakes.repositories.BetRepository;
import kg.bektur.hippodromesweepstakes.repositories.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RaceResultService {

    private final RaceRepository raceRepository;

    private final BetRepository betRepository;

    private final UserService userService;

    private final BetService betService;

    private final RaceService raceService;

    private final HorseService horseService;

    @Autowired
    public RaceResultService(RaceRepository raceRepository, BetRepository betRepository, UserService userService, BetService betService, RaceService raceService, HorseService horseService) {
        this.raceRepository = raceRepository;
        this.betRepository = betRepository;
        this.userService = userService;
        this.betService = betService;
        this.raceService = raceService;
        this.horseService = horseService;
    }

    @PostConstruct
    public void init() {
        List<Race> races = raceRepository.findAll();
        for (Race race : races) {
            if (race.getRaceTime().isBefore(LocalDateTime.now())) {
                double totalBetAmount = race.getHorses().stream()
                        .flatMap(horse -> horse.getBets().stream())
                        .mapToDouble(Bet::getAmount)
                        .sum();

                List<Long> horseIds = race.getHorses().stream()
                        .map(AbstractEntity::getId)
                        .collect(Collectors.toList());

                Collections.shuffle(horseIds);
                double winBetAmount = race.getHorses().stream()
                        .filter(horse -> Objects.equals(horse.getId(), horseIds.get(0)))
                        .flatMap(horse -> horse.getBets().stream())
                        .mapToDouble(Bet::getAmount)
                        .sum();

                accountCalculations(horseIds.get(0), totalBetAmount, horseIds.size(), winBetAmount);

                // Delete the race and its horses
                race.getHorses().forEach(horse -> horseService.withdrawFromRace(horse.getId()));
                raceService.delete(race.getId());
            }
        }
    }

    public void accountCalculations(Long winId, double totalBetAmount, int numOfParticipants, double winBetAmount) {
        System.out.println(winId + " " + totalBetAmount + " " + numOfParticipants + " " + winBetAmount);
        for (Bet bet : betRepository.findAll()) {
            System.out.println(bet.getUser().getId());
            if (Objects.equals(bet.getHorse().getId(), winId)) {
                bet.setWin(true);
            }
            if (bet.isWin()) {
                double result = bet.getAmount() * (totalBetAmount * ((double) numOfParticipants / 10) / winBetAmount);
                userService.updateBalance(result, bet.getUser().getId());
            }
            betService.delete(bet.getId());
        }
    }

}
