package kg.bektur.hippodromesweepstakes.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Horse")
@Where(clause = "deleted = false")
public class Horse extends AbstractEntity {

    @NotEmpty(message = "The name should not be empty")
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "horse", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Bet> bets;

    @ManyToOne
    private Race race;

}
