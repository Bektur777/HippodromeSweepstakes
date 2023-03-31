package kg.bektur.hippodromesweepstakes.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class RaceDto extends AbstractDto {

    private Date raceTime;

}
