package kg.bektur.hippodromesweepstakes.repositories;

import kg.bektur.hippodromesweepstakes.entities.Bet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {

    @Modifying
    @Query("update Bet b set b.deleted = true where b.id = :bet_id")
    void softDelete(@Param("bet_id") Long id);

}
