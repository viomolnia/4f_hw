package io.fourfinanceit.core.database.users;

import io.fourfinanceit.core.database.UserDAO;
import io.fourfinanceit.core.domain.user.User;
import io.fourfinanceit.core.services.users.UserValidator;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

/**
 * Created by Anna on 03.02.2016.
 */
@Component
@Transactional
public class UserDAOImpl extends CRUDOperationDAOImpl<User, Long> implements UserDAO {

    int maxFailedLoginAttempt = 5;

    @Autowired
    private UserValidator userValidator;


    @Override
    public User getUserByPersonCode(String personCode){
        return (User) getCurrentSession()
                .createCriteria(User.class)
                .add(Restrictions
                        .eq("personCode", personCode))
                .uniqueResult();
    }
}