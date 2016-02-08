package io.fourfinanceit.core.services.extensions;

import io.fourfinanceit.core.database.ExtensionDAO;
import io.fourfinanceit.core.database.LoanDAO;
import io.fourfinanceit.core.database.UserDAO;
import io.fourfinanceit.core.domain.loan.Loan;
import io.fourfinanceit.core.domain.user.User;
import io.fourfinanceit.core.services.loans.LoanFactory;
import io.fourfinanceit.core.services.loans.LoanFactoryImpl;
import io.fourfinanceit.core.services.loans.LoanValidator;
import io.fourfinanceit.core.services.users.UserFactory;
import io.fourfinanceit.core.services.users.UserFactoryImpl;
import io.fourfinanceit.core.services.users.UserValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Calendar;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

/**
 * Created by Anna on 08.02.2016.
 */

@RunWith(MockitoJUnitRunner.class)

public class ExtensionFactoryImplTest {

    @Mock   private ExtensionValidator extensionValidator;
    @Mock   private LoanValidator loanValidator;
    @Mock   private UserValidator userValidator;
    @Mock   private LoanDAO loanDAO;
    @Mock   private UserDAO userDAO;
    @Mock   private ExtensionDAO extensionDAO;

    @InjectMocks    private LoanFactory loanFactory = new LoanFactoryImpl();
    @InjectMocks    private UserFactory userFactory = new UserFactoryImpl();
    @InjectMocks    private ExtensionFactory extensionFactory = new ExtensionFactoryImpl();

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PERSONCODE30 = "123499-19873";
    private static final String PERSONCODE31 = "123488-19873";

    private static final int TERM = 20;
    private static final BigDecimal AMOUNT = new BigDecimal("100");
    private static int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);


    @Test
    public void createShouldInvokeValidator() {
        User user = userFactory.create(NAME, SURNAME, PERSONCODE30);
        doReturn(user)
                .when(userDAO).getById(2L);
        Loan loan = loanFactory.create(TERM, AMOUNT, 2L);
        doReturn(loan)
                .when(loanDAO).getById(2L);
        extensionFactory.create(1, 2L);
        verify(extensionValidator).validate(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createShouldFailIfNegativeExtensionTerm() {
        User user = userFactory.create(NAME, SURNAME, PERSONCODE31);
        doReturn(user)
                .when(userDAO).getById(3L);
        Loan loan = loanFactory.create(TERM, AMOUNT, 3L);
        doReturn(loan)
                .when(loanDAO).getById(3L);
        doThrow(new IllegalArgumentException())
                .when(extensionValidator).validate(-5);
        extensionFactory.create(-5, 3L);

    }

}
