package kg.bektur.hippodromesweepstakes.services;

import jakarta.annotation.PostConstruct;
import kg.bektur.hippodromesweepstakes.dto.RaceDto;
import kg.bektur.hippodromesweepstakes.dto.RaceFullDto;
import kg.bektur.hippodromesweepstakes.entities.Horse;
import kg.bektur.hippodromesweepstakes.entities.Race;
import kg.bektur.hippodromesweepstakes.entities.User;
import kg.bektur.hippodromesweepstakes.repositories.HorseRepository;
import kg.bektur.hippodromesweepstakes.repositories.RaceRepository;
import kg.bektur.hippodromesweepstakes.repositories.UserRepository;
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

    private final UserRepository userRepository;

    @Autowired
    public AdminService(HorseRepository horseRepository, ModelMapper mapper, RaceRepository raceRepository, UserRepository userRepository) {
        this.horseRepository = horseRepository;
        this.mapper = mapper;
        this.raceRepository = raceRepository;
        this.userRepository = userRepository;
    }

    public Optional<RaceFullDto> getHorsesRace(Long id) {
        return Optional.ofNullable(mapper.map(horseRepository.findById(id).map(Horse::getRace), RaceFullDto.class)
        );
    }

    @Transactional
    public void assign(Long id, RaceDto raceDto) {
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

    @PostConstruct
    @Transactional
    public void createAdmin() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User user = new User();
            user.setUsername("admin");
            user.setPassword("$2a$10$qVz23Zjfc2SkanXyGl5PUe8Qtra3cQ7y7aQItwsZD3ULfxQxkdL7i");
            user.setFullName("Admin Admin");
            user.setRole("ROLE_ADMIN");
            user.setAge(20);
            userRepository.save(user);
        }
    }

}
