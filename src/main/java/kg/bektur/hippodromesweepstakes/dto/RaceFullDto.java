package kg.bektur.hippodromesweepstakes.dto;

import kg.bektur.hippodromesweepstakes.entities.Horse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RaceFullDto extends AbstractDto {

    private Date raceTime;

    private List<Horse> horses;

}
