package kg.bektur.hippodromesweepstakes.utils;

import kg.bektur.hippodromesweepstakes.entities.User;
import kg.bektur.hippodromesweepstakes.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final AuthorizationService authorizationService;

    @Autowired
    public UserValidator(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User person = (User) target;

        if (authorizationService.findPersonByUsername(person.getUsername()).isPresent())
            errors.rejectValue("username", "403", "Username is already exist");
    }

}
