package kg.bektur.hippodromesweepstakes.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Race")
public class Race extends AbstractEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "race_time")
    private Date raceTime;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL)
    private List<Horse> horses;

}