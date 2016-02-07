package io.fourfinanceit.core.services.users;

/**
 * Created by Anna on 03.02.2016.
 */

import io.fourfinanceit.core.database.UserDAO;
import io.fourfinanceit.core.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired private UserDAO userDAO;
    @Autowired private UserValidator userValidator;


    @Override
    public User get(Long userId) {
        return userDAO.getRequired(userId);
    }

    @Override
    public User get(String personCode) {
        return userDAO.getUserByPersonCode(personCode);
    }
}
