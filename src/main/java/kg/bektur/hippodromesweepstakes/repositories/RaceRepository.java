package kg.bektur.hippodromesweepstakes.repositories;

import kg.bektur.hippodromesweepstakes.entities.Race;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RaceRepository extends JpaRepository<Race, Long> {
}
