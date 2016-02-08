package io.fourfinanceit.core.services.extensions;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anna on 08.02.2016.
 */
public class ExtensionValidatorImplTest {

    private ExtensionValidator validator = new ExtensionValidatorImpl();

    @Test
    public void validateShouldFailIfTermIsNegative() {
        validateShouldFail(-5, "Loan extension term must be at least one week");
    }

    private void validateShouldFail(int extTerm,
                                    String errorMessage) {
        try {
            validator.validate(extTerm);
            fail();
        } catch (Exception e) {
            assertThat(e.getMessage(), is(errorMessage));
        }
    }
}
