package kg.bektur.hippodromesweepstakes.repositories;

import kg.bektur.hippodromesweepstakes.entities.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {

    @Modifying
    @Query("update Race c set c.deleted = true where c.id = :race_id")
    void softDelete(@Param("race_id") Long id);

}
