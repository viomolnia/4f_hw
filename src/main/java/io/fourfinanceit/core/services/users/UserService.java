package io.fourfinanceit.core.services.users;

import io.fourfinanceit.core.domain.user.User;

/**
 * Created by Anna on 03.02.2016.
 */
public interface UserService {

    // get user by his ID
    User get(Long userId);

    //get user by his person code
    User get(String personCode);

}
