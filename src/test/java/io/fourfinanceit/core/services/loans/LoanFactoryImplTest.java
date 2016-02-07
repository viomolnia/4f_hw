package io.fourfinanceit.core.services.loans;

/**
 * Created by Anna on 06.02.2016.
 */

import io.fourfinanceit.core.database.LoanDAO;
import io.fourfinanceit.core.database.UserDAO;
import io.fourfinanceit.core.domain.loan.Loan;
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

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoanFactoryImplTest {

    @Mock   private LoanValidator loanValidator;
    @Mock   private UserValidator userValidator;
    @Mock   private LoanDAO loanDAO;
    @Mock   private UserDAO userDAO;

    @InjectMocks    private LoanFactory loanFactory = new LoanFactoryImpl();
    @InjectMocks    private UserFactory userFactory = new UserFactoryImpl();

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PERSONCODE16 = "123459-19873";
    private static final String PERSONCODE17 = "123458-29872";
    private static final String PERSONCODE18 = "123457-39871";
    private static final String PERSONCODE19 = "123456-39871";

    private static final int TERM = 20;
    private static final BigDecimal AMOUNT = new BigDecimal("100");

    @Test
    public void createShouldInvokeValidator() {
        User user = userFactory.create(NAME, SURNAME, PERSONCODE16);
        doReturn(user)
                .when(userDAO).getById(user.getUserId());
        loanFactory.create(TERM, AMOUNT, user.getUserId());
        verify(loanValidator).validate(TERM, AMOUNT);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createShouldFailIfValidationFail() {
        User user = userFactory.create(NAME, SURNAME, PERSONCODE17);
        doReturn(user)
                .when(userDAO).getById(user.getUserId());
        doThrow(new IllegalArgumentException())
                .when(loanValidator).validate(TERM, AMOUNT);
        loanFactory.create(TERM, AMOUNT, user.getUserId());
    }


    @Test
    public void createShouldPersistLoanAfterValidation() {
        User user = userFactory.create(NAME, SURNAME, PERSONCODE18);
        doReturn(user)
                .when(userDAO).getById(user.getUserId());

        Loan loan = loanFactory.create(TERM, AMOUNT, user.getUserId());
        InOrder inOrder = inOrder( loanValidator, loanDAO);

        inOrder.verify(loanValidator).validate(TERM, AMOUNT);
        inOrder.verify(loanDAO).create(loan);
    }

    @Test
    public void createShouldSetLoanReturnDate() {

        BigDecimal amount = new BigDecimal("100");
        int term = 20;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Calendar calFrom = Calendar.getInstance();
        Calendar calTo = Calendar.getInstance();

        User user = userFactory.create(NAME, SURNAME, PERSONCODE19);
        doReturn(user)
                .when(userDAO).getById(user.getUserId());

        Loan loan = loanFactory.create(term, amount, user.getUserId());

        try {
            calFrom.setTime(dateFormat.parse(loan.getDateTaken()));
            calFrom.add(Calendar.DATE, term);
            calTo.setTime(dateFormat.parse(loan.getDateReturn()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        assertSame(loan.getTerm(), term);
        assertEquals(calFrom, calTo);
    }

    @Test
    public void createShouldReturnNewLoanWithoutIndexing() {

        //interest = 0

        BigDecimal amount = new BigDecimal("100");
        int term = 20;

        User user = userFactory.create(NAME, SURNAME, PERSONCODE19);
        doReturn(user)
                .when(userDAO).getById(user.getUserId());

        Loan loan = loanFactory.create(term, amount, user.getUserId());
        assertSame(loan.getAmount(), amount);
        assertEquals(loan.getIndexedAmount(), amount);

    }

    @Test
    public void createShouldReturnNewLoanWithIndexing() {

        //interest = 1.04

        BigDecimal amount = new BigDecimal("350");
        int term = 20;

        User user = userFactory.create(NAME, SURNAME, PERSONCODE19);
        doReturn(user)
                .when(userDAO).getById(user.getUserId());

        Loan loan = loanFactory.create(term, amount, user.getUserId());
        assertSame(loan.getAmount(), amount);
        assertEquals(loan.getIndexedAmount(), new BigDecimal("364.00") );

    }

}