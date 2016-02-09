package io.fourfinanceit.core.services.users;

import io.fourfinanceit.core.domain.user.User;

/**
 * Created by Anna on 03.02.2016.
 */
public interface UserFactory {

    //create user with name, surname and person code
    User create(String name, String surname, String personCode);

}