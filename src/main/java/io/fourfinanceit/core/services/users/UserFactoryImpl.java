package io.fourfinanceit.core.services.users;

/**
 * Created by Anna on 03.02.2016.
 */

import io.fourfinanceit.core.database.UserDAO;
import io.fourfinanceit.core.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static io.fourfinanceit.core.domain.user.UserBuilder.createUser;

@Component
public class UserFactoryImpl implements UserFactory {

    @Autowired private UserValidator userValidator;
    @Autowired private UserDAO userDAO;


    @Override
    public User create(String name, String surname, String personCode) {
        userValidator.validate(name, surname);
        if(userDAO.getUserByPersonCode(personCode) == null){
            User user = createUser()
                    .withName(name)
                    .withSurname(surname)
                    .withPersonCode(personCode)
                    .build();

            userDAO.create(user);
            return user;
        } else {
            return null;
        }
    }
}
