package kg.bektur.hippodromesweepstakes.services;

import kg.bektur.hippodromesweepstakes.dto.BetDto;
import kg.bektur.hippodromesweepstakes.entities.Bet;
import kg.bektur.hippodromesweepstakes.entities.Horse;
import kg.bektur.hippodromesweepstakes.entities.User;
import kg.bektur.hippodromesweepstakes.repositories.BetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BetService {

    private final BetRepository betRepository;

    private final ModelMapper mapper;

    private final AuthorizationService authorizationService;

    private final HorseService horseService;

    public BetService(BetRepository betRepository, ModelMapper mapper, AuthorizationService authorizationService, HorseService horseService) {
        this.betRepository = betRepository;
        this.mapper = mapper;
        this.authorizationService = authorizationService;
        this.horseService = horseService;
    }

    @Transactional
    public void save(Long horse_id, BetDto betDto) {
        Bet bet = mapper.map(betDto, Bet.class);
        User user = authorizationService.getCurrentUser();
        bet.setUser(user);
        bet.setHorse(mapper.map(horseService.findHorseById(horse_id), Horse.class));
        betRepository.save(bet);
    }

    @Transactional
    public void delete(Long id) {
        betRepository.softDelete(id);
    }

}
