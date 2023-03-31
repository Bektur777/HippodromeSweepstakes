package kg.bektur.hippodromesweepstakes.services;

import kg.bektur.hippodromesweepstakes.dto.RaceFullDto;
import kg.bektur.hippodromesweepstakes.entities.Horse;
import kg.bektur.hippodromesweepstakes.entities.Race;
import kg.bektur.hippodromesweepstakes.repositories.HorseRepository;
import kg.bektur.hippodromesweepstakes.repositories.RaceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AdminService {

    private final HorseRepository horseRepository;

    private final ModelMapper mapper;

    public final RaceRepository raceRepository;

    @Autowired
    public AdminService(HorseRepository horseRepository, ModelMapper mapper, RaceRepository raceRepository) {
        this.horseRepository = horseRepository;
        this.mapper = mapper;
        this.raceRepository = raceRepository;
    }

    public Optional<RaceFullDto> getHorsesRace(Long id) {
        return Optional.ofNullable(mapper.map(horseRepository.findById(id).map(Horse::getRace), RaceFullDto.class)
        );
    }

    @Transactional
    public void assign(Long id, RaceFullDto raceDto) {
        horseRepository.findById(id).ifPresent(
                horse -> horse.setRace(mapper.map(raceDto, Race.class))
        );
    }

    @Transactional
    public void withdrawHorse(Long id) {
        horseRepository.findById(id).ifPresent(
                horse -> horse.setRace(null)
        );
    }

}
