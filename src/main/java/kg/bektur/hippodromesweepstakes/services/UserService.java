package kg.bektur.hippodromesweepstakes.services;

import kg.bektur.hippodromesweepstakes.entities.User;
import kg.bektur.hippodromesweepstakes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void updateBalance(double balance, Long id) {
        userRepository.updateBalance(balance, id);
    }

}
