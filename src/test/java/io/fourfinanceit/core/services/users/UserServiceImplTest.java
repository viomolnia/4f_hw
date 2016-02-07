package io.fourfinanceit.core.services.users;

/**
 * Created by Anna on 06.02.2016.
 */

import io.fourfinanceit.core.database.UserDAO;
import io.fourfinanceit.core.domain.user.User;
import io.fourfinanceit.core.services.users.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock    private UserValidator userValidator;
    @Mock    private UserDAO userDAO;

    @InjectMocks    private UserFactory userFactory = new UserFactoryImpl();
    @InjectMocks    private UserService userService = new UserServiceImpl();

    private static final String PERSCODE = "000000-11111";

    @Test
    public void getShouldReturnNewUser() {

        User gotUser = userService.get(PERSCODE);
        InOrder inOrder = inOrder(userDAO);
        inOrder.verify(userDAO).getUserByPersonCode(PERSCODE);
    }


}
