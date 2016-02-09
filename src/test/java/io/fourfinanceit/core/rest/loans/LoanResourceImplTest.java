package io.fourfinanceit.core.rest.loans;

/**
 * Created by Anna on 06.02.2016.
 */

import io.fourfinanceit.core.commands.extensions.ExtensionConverter;
import io.fourfinanceit.core.commands.users.UserConverter;
import io.fourfinanceit.core.database.ExtensionDAO;
import io.fourfinanceit.core.database.LoanDAO;
import io.fourfinanceit.core.database.UserDAO;
import io.fourfinanceit.core.domain.extension.Extension;
import io.fourfinanceit.core.dto.extension.ExtensionDTO;
import io.fourfinanceit.core.dto.loan.LoanDTO;
import io.fourfinanceit.core.dto.user.UserDTO;
import io.fourfinanceit.core.rest.RESTResourceTest;
import io.fourfinanceit.core.services.extensions.ExtensionFactory;
import io.fourfinanceit.core.services.extensions.ExtensionFactoryImpl;
import io.fourfinanceit.core.services.loans.LoanFactory;
import io.fourfinanceit.core.services.loans.LoanFactoryImpl;
import io.fourfinanceit.core.services.users.UserFactory;
import io.fourfinanceit.core.services.users.UserFactoryImpl;
import io.fourfinanceit.core.services.users.UserValidator;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Iterator;

import static io.fourfinanceit.core.dto.extension.ExtensionDTOBuilder.createExtensionDTO;
import static io.fourfinanceit.core.dto.loan.LoanDTOBuilder.createLoanDTO;
import static io.fourfinanceit.core.dto.user.UserDTOBuilder.createUserDTO;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.*;

/**
 * Created by Anna on 22.11.2015.
 */
public class LoanResourceImplTest extends RESTResourceTest {

    @Mock   private LoanDAO loanDAO;
    @Mock   private UserValidator userValidator;
    @Mock   private UserDAO userDAO;

    @InjectMocks    private UserFactory userFactory = new UserFactoryImpl();
    @InjectMocks    private LoanFactory loanFactory = new LoanFactoryImpl();

    @Autowired
    UserConverter userConverter;

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PERSONCODE13 = "123456-49873";
    private static final String PERSONCODE14 = "123456-59872";
    private static final String PERSONCODE15 = "123456-69871";

    private static final int TERM = 20;
    private static final BigDecimal AMOUNT = new BigDecimal("100.00");


    @Test
    public void createLoanTest() {

        UserDTO user = userResource.create(
                createUserDTO()
                        .withName(NAME)
                        .withSurname(SURNAME)
                        .withPersonCode(PERSONCODE13)
                        .build()
        );
        assertThat(user, is(notNullValue()));
        assertThat(user.getUserId(), is(notNullValue()));

        LoanDTO loan1 = loanResource.create(
                createLoanDTO()
                        .withTerm(TERM)
                        .withAmount(AMOUNT)
                        .withUserDTO(user)
                        .build()
        );
        assertThat(loan1, is(notNullValue()));
    }

    @Test
    public void getLoanTest() {

        UserDTO user = userResource.create(
                createUserDTO()
                        .withName(NAME)
                        .withSurname(SURNAME)
                        .withPersonCode(PERSONCODE14)
                        .build()
        );
        assertThat(user, is(notNullValue()));
        assertThat(user.getUserId(), is(notNullValue()));

        LoanDTO newLoan = loanResource.create(
                createLoanDTO()
                        .withTerm(TERM)
                        .withAmount(AMOUNT)
                        .withUserDTO(user)
                        .build()
        );

        assertThat(newLoan, is(notNullValue()));
        LoanDTO oldLoan = loanResource.get(newLoan.getLoanId());
        assertThat(newLoan.getLoanId(), is(oldLoan.getLoanId()));
    }

}