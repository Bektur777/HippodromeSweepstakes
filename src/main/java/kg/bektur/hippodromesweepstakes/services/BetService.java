package kg.bektur.hippodromesweepstakes.services;

import kg.bektur.hippodromesweepstakes.dto.BetDto;
import kg.bektur.hippodromesweepstakes.entities.Bet;
import kg.bektur.hippodromesweepstakes.repositories.BetRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BetService {

    private final BetRepository betRepository;

    private final ModelMapper mapper;

    public BetService(BetRepository betRepository, ModelMapper mapper) {
        this.betRepository = betRepository;
        this.mapper = mapper;
    }

    @Transactional
    public void save(BetDto betDto) {
        betRepository.save(mapper.map(betDto, Bet.class));
    }

}
