package kg.bektur.hippodromesweepstakes.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Race")
@Where(clause = "deleted = false")
public class Race extends AbstractEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "race_time")
    private LocalDateTime raceTime;

    @OneToMany(mappedBy = "race", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Horse> horses;

}