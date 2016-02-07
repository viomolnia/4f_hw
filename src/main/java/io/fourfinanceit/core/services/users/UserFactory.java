package io.fourfinanceit.core.services.users;

import io.fourfinanceit.core.domain.user.User;

/**
 * Created by Anna on 03.02.2016.
 */
public interface UserFactory {

    User create(String name, String surname, String personCode);

}