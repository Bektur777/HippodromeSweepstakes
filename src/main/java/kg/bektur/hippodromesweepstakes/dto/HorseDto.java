package kg.bektur.hippodromesweepstakes.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HorseDto extends AbstractDto {

    @NotEmpty(message = "The name should not be empty")
    private String name;

}
