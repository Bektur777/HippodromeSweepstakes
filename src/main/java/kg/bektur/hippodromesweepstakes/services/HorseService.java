package kg.bektur.hippodromesweepstakes.services;

import kg.bektur.hippodromesweepstakes.dto.HorseDto;
import kg.bektur.hippodromesweepstakes.dto.HorseFullDto;
import kg.bektur.hippodromesweepstakes.entities.Horse;
import kg.bektur.hippodromesweepstakes.repositories.HorseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HorseService {

    private final HorseRepository horseRepository;

    private final ModelMapper mapper;

    @Autowired
    public HorseService(HorseRepository horseRepository, ModelMapper mapper) {
        this.horseRepository = horseRepository;
        this.mapper = mapper;
    }

    public List<HorseDto> findAllHorses() {
        return horseRepository.findAll().stream()
                .map(horse -> mapper.map(horse, HorseDto.class))
                .collect(Collectors.toList()
        );
    }

    public HorseFullDto findHorseById(Long id) {
        return mapper.map(horseRepository.findById(id).orElseThrow(Error::new), HorseFullDto.class);
    }

    @Transactional
    public void save(HorseDto horseDto) {
        horseRepository.save(mapper.map(horseDto, Horse.class));
    }

    @Transactional
    public void delete(Long id) {
        horseRepository.deleteHorseById(id);
    }

}
