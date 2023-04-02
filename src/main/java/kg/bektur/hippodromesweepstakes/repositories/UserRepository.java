package kg.bektur.hippodromesweepstakes.repositories;

import kg.bektur.hippodromesweepstakes.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    @Modifying
    @Query("update User u set u.balance = u.balance + :balance where u.id=:user_id")
    void updateBalance(@Param("balance") double balance, @Param("user_id") Long user_id);

}
