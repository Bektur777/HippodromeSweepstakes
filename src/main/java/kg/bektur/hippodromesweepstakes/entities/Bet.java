package kg.bektur.hippodromesweepstakes.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Bet")
public class Bet extends AbstractEntity {

    // Сумма ставки
    @Column(name = "amount")
    private double amount;

    // Flag, указывающий, выиграла ли ставка (true - выиграла)
    // P.s: Поля, помеченные аннотацией @Transient не будут сохраняться в БД
    @Transient
    private boolean win;

    // Сумма выигрыша
    @Column(name = "result")
    private double result;

    @ManyToOne
    private User user;

    @ManyToOne
    private Horse horse;

}
