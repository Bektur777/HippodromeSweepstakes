package kg.bektur.hippodromesweepstakes.dto;

import jakarta.validation.constraints.NotEmpty;
import kg.bektur.hippodromesweepstakes.entities.Bet;
import kg.bektur.hippodromesweepstakes.entities.Race;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HorseFullDto extends AbstractDto {

    @NotEmpty(message = "The name should not be empty")
    private String name;

    private List<Bet> bets;

    private Race race;

}
