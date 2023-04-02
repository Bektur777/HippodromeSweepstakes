package kg.bektur.hippodromesweepstakes.repositories;

import kg.bektur.hippodromesweepstakes.entities.Horse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HorseRepository extends JpaRepository<Horse, Long> {

    @Modifying
    @Query("update Horse h set h.deleted = true where h.id = :horse_id")
    void softDelete(@Param("horse_id") Long id);

    @Modifying
    @Query("update Horse h set h.race = null where h.id = :horse_id")
    void withdraw(@Param("horse_id") Long id);

}
