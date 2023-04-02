package kg.bektur.hippodromesweepstakes.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Person")
@Where(clause = "deleted = false")
public class User extends AbstractEntity {

    @NotEmpty(message = "The username should not be empty")
    @Size(min = 3, max = 100, message = "The username should be between 3 and 100")
    @Column(name = "username")
    private String username;

    @NotEmpty(message = "The password should not be empty")
    @Column(name = "password")
    private String password;

    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "The full name should be like: Sam Smith")
    @Column(name = "full_name")
    private String fullName;

    @Min(value = 18, message = "The age should be between 18 and 100")
    @Max(value = 100, message = "The age should be between 18 and 100")
    @Column(name = "age")
    private Integer age;

    @Column(name = "balance")
    private double balance;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Bet> bets;

}
