package kg.bektur.hippodromesweepstakes.dto;

import kg.bektur.hippodromesweepstakes.entities.Horse;
import kg.bektur.hippodromesweepstakes.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BetDto {

    private double amount;

    private User user;

    private Horse horse;

}
