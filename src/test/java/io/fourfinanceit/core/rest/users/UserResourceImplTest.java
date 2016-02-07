package io.fourfinanceit.core.rest.users;

/**
 * Created by Anna on 06.02.2016.
 */

import io.fourfinanceit.core.dto.loan.LoanDTO;
import io.fourfinanceit.core.dto.user.UserDTO;
import io.fourfinanceit.core.rest.RESTResourceTest;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

import static io.fourfinanceit.core.dto.loan.LoanDTOBuilder.createLoanDTO;
import static io.fourfinanceit.core.dto.user.UserDTOBuilder.createUserDTO;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;


public class UserResourceImplTest extends RESTResourceTest {

    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PERSONCODE4 = "123456-09873";
    private static final String PERSONCODE5 = "123456-09872";
    private static final String PERSONCODE6 = "123456-09871";

    private static final int TERM = 20;
    private static final BigDecimal AMOUNT = new BigDecimal("100");




    @Test
    public void createUserTest() throws IOException {
        UserDTO user = userResource.create(
                createUserDTO()
                        .withName(NAME)
                        .withSurname(SURNAME)
                        .withPersonCode(PERSONCODE4)
                        .build()
        );
        assertThat(user, is(notNullValue()));
        assertThat(user.getUserId(), is(notNullValue()));
    }

    @Test
    public void getUserByIdTest() {
        UserDTO newUser = userResource.create(
                createUserDTO()
                        .withName(NAME)
                        .withSurname(SURNAME)
                        .withPersonCode(PERSONCODE5)
                        .build()
        );
        UserDTO oldUser = userResource.get(newUser.getUserId());
        assertThat(newUser.getUserId(), is(oldUser.getUserId()));
        assertThat(newUser.getName(), is(NAME));
        assertThat(newUser.getSurname(), is(SURNAME));
    }

    @Test
    public void getUserLoansTest() {

        UserDTO user = userResource.create(
                createUserDTO()
                        .withName(NAME)
                        .withSurname(SURNAME)
                        .withPersonCode(PERSONCODE6)
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

        LoanDTO loan2 = loanResource.create(
                createLoanDTO()
                        .withTerm(TERM)
                        .withAmount(AMOUNT)
                        .withUserDTO(user)
                        .build()
        );

        Set<LoanDTO> userLoans = userResource.getUserLoans(user.getUserId());
        assertThat(userLoans.size(), is(2));
    }

}
