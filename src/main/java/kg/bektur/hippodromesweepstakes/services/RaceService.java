package kg.bektur.hippodromesweepstakes.services;

import kg.bektur.hippodromesweepstakes.dto.RaceDto;
import kg.bektur.hippodromesweepstakes.dto.RaceFullDto;
import kg.bektur.hippodromesweepstakes.repositories.RaceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class RaceService {

    private final RaceRepository raceRepository;

    private final ModelMapper mapper;

    @Autowired
    public RaceService(RaceRepository raceRepository, ModelMapper mapper) {
        this.raceRepository = raceRepository;
        this.mapper = mapper;
    }

    // Список всех не заполненных лошадьми скачки
    public List<RaceFullDto> findAllNotReserveRaces() {
        return raceRepository.findAll().stream()
                .filter(race -> race.getHorses().size() <= 7)
                .map(race -> mapper.map(race, RaceFullDto.class))
                .collect(Collectors.toList());
    }

    public List<RaceDto> findAllRaces() {
        return raceRepository.findAll().stream()
                .map(race -> mapper.map(race, RaceDto.class))
                .collect(Collectors.toList());
    }

}
