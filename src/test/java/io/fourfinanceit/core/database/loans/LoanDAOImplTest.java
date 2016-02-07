package io.fourfinanceit.core.database.loans;

import io.fourfinanceit.core.domain.loan.Loan;
import io.fourfinanceit.core.domain.user.User;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static io.fourfinanceit.core.domain.user.UserBuilder.createUser;
import static io.fourfinanceit.core.domain.loan.LoanBuilder.createLoan;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * Created by Anna on 06.02.2016.
 */
public class LoanDAOImplTest extends DatabaseHibernateTest {

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PERSONCODE10 = "123456-19873";
    private static final String PERSONCODE11 = "123456-29872";
    private static final String PERSONCODE12 = "123456-39871";

    private static final int TERM = 20;
    private static final BigDecimal AMOUNT = new BigDecimal("100");




    @Test
    @Transactional
    public void testCreateLoanWithAll() {
        User user = createUser()
                .withName(NAME)
                .withSurname(SURNAME)
                .withPersonCode(PERSONCODE10)
                .build();

        Loan loan = createLoan()
                        .withTerm(TERM)
                        .withAmount(AMOUNT)
                        .withUser(user)
                        .build();

        Set<Loan> loans = new HashSet<Loan>();

        loans.add(loan);
        user.setLoans(loans);

        loanDAO.create(loan);
        userDAO.create(user);
        assertThat(loan.getLoanId(), is(notNullValue()));
    }

    @Test
    @Transactional
    public void testGetLoanById() {
        User user = createUser()
                .withName(NAME)
                .withSurname(SURNAME)
                .withPersonCode(PERSONCODE11)
                .build();

        Loan loan = createLoan()
                .withTerm(TERM)
                .withAmount(AMOUNT)
                .withUser(user)
                .build();

        Set<Loan> loans = new HashSet<Loan>();

        loans.add(loan);
        user.setLoans(loans);

        loanDAO.create(loan);
        userDAO.create(user);
        Loan loanFromDb = loanDAO.getById(loan.getLoanId());
        assertThat(loanFromDb, is(notNullValue()));
    }

    @Test
    @Transactional
    public void testDeleteLoan() {

        User user = createUser()
                .withName(NAME)
                .withSurname(SURNAME)
                .withPersonCode(PERSONCODE12)
                .build();

        Loan loan = createLoan()
                .withTerm(TERM)
                .withAmount(AMOUNT)
                .withUser(user)
                .build();

        Set<Loan> loans = new HashSet<Loan>();

        loans.add(loan);
        user.setLoans(loans);

        loanDAO.create(loan);
        userDAO.create(user);

        Loan loanFromDb = loanDAO.getById(loan.getLoanId());
        assertThat(loanFromDb, is(notNullValue()));
        loanDAO.delete(loanFromDb);
        loanFromDb = loanDAO.getById(loan.getLoanId());
        assertThat(loanFromDb, is(nullValue()));
    }

}
