package kg.bektur.hippodromesweepstakes.repositories;

import jakarta.websocket.server.PathParam;
import kg.bektur.hippodromesweepstakes.entities.Horse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HorseRepository extends JpaRepository<Horse, Long> {

    @Query("update Horse h set h.deleted = true where h.id = :horse_id")
    void deleteHorseById(@PathParam("horse_id") Long id);

}
