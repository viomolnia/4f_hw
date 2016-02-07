package io.fourfinanceit.core.database;

import io.fourfinanceit.core.domain.user.User;

/**
 * Created by Anna on 03.02.2016.
 */
public interface UserDAO extends CRUDOperationDAO<User, Long>{

    User getUserByPersonCode(String personCode);
}
