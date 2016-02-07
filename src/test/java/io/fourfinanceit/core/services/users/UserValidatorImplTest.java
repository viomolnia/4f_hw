package io.fourfinanceit.core.services.users;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anna on 06.02.2016.
 */
public class UserValidatorImplTest {
    private UserValidator validator = new UserValidatorImpl();


    private static final String NAME = "name";
    private static final String SURNAME = "surname";

    @Test
    public void validateShouldFailIfNameIsNull() {
        validateShouldFail(null, SURNAME, "User name must not be null");
    }

    @Test
    public void validateShouldFailIfNameIsEmpty() {
        validateShouldFail("", SURNAME, "User name must not be empty");
    }

    @Test
    public void validateShouldFailIfSurnameIsNull() {
        validateShouldFail(NAME, null, "User surname must not be null");
    }

    @Test
    public void validateShouldFailIfSurnameIsEmpty() {
        validateShouldFail(NAME, "", "User surname must not be empty");
    }

    private void validateShouldFail(
                                    String name,
                                    String surname,
                                    String errorMessage) {
        try {
            validator.validate(name, surname);
            fail();
        } catch (Exception e) {
            assertThat(e.getMessage(), is(errorMessage));
        }
    }

}
