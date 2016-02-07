package io.fourfinanceit.core.services.users;

/**
 * Created by Anna on 06.02.2016.
 */

import io.fourfinanceit.core.database.UserDAO;
import io.fourfinanceit.core.domain.user.User;
import io.fourfinanceit.core.services.users.UserFactory;
import io.fourfinanceit.core.services.users.UserFactoryImpl;
import io.fourfinanceit.core.services.users.UserValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserFactoryImplTest {

    @Mock   private UserValidator userValidator;
    @Mock   private UserDAO userDAO;

    @InjectMocks    private UserFactory userFactory = new UserFactoryImpl();

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PERSONCODE7 = "123456-09873";
    private static final String PERSONCODE8 = "123456-09872";
    private static final String PERSONCODE9 = "123456-09871";

    @Test
    public void createShouldInvokeValidator() {
        userFactory.create(NAME, SURNAME, PERSONCODE7);
        verify(userValidator).validate(NAME, SURNAME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createShouldFailIfValidationFail() {
        doThrow(new IllegalArgumentException())
                .when(userValidator).validate(NAME, "");
        userFactory.create(NAME, "", PERSONCODE8);
    }

    @Test
    public void createShouldPersistUserAfterValidation() {
        User user = userFactory.create(NAME, SURNAME, PERSONCODE8);
        InOrder inOrder = inOrder(userValidator, userDAO, userDAO);
        inOrder.verify(userValidator).validate(NAME, SURNAME);
        inOrder.verify(userDAO).getUserByPersonCode(PERSONCODE8);
        inOrder.verify(userDAO).create(user);
    }

    @Test
    public void createShouldReturnNewUser() {
        User user = userFactory.create(NAME, SURNAME, PERSONCODE9);
        assertThat(user.getName(), is(NAME));
        assertThat(user.getSurname(), is(SURNAME));
        assertThat(user.getPersonCode(), is(PERSONCODE9));
    }
}