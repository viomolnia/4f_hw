package io.fourfinanceit.core.database.users;

/**
 * Created by Anna on 06.02.2016.
 */

import io.fourfinanceit.core.domain.user.User;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static io.fourfinanceit.core.domain.user.UserBuilder.createUser;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class UseDAOImplTest extends DatabaseHibernateTest {

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PERSONCODE1 = "123456-09876";
    private static final String PERSONCODE2 = "123456-09875";
    private static final String PERSONCODE3 = "123456-09874";

    @Test
    @Transactional
    public void testCreateUserWithAll() {
        User user = createUser()
                .withName(NAME)
                .withSurname(SURNAME)
                .withPersonCode(PERSONCODE1)
                .build();
        assertThat(user.getUserId(), is(nullValue()));
        userDAO.create(user);
        assertThat(user.getUserId(), is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetUserById() {
        User user = createUser()
                .withName(NAME)
                .withSurname(SURNAME)
                .withPersonCode(PERSONCODE2)
                .build();
        userDAO.create(user);
        User userFromDb = userDAO.getById(user.getUserId());
        assertThat(userFromDb, is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetUserByPersCode() {
        User user = createUser()
                .withName(NAME)
                .withSurname(SURNAME)
                .withPersonCode(PERSONCODE3)
                .build();
        userDAO.create(user);
        assertThat(userDAO.getUserByPersonCode(PERSONCODE3), is(notNullValue()));
    }

}
