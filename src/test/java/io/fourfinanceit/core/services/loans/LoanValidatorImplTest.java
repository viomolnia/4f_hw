package io.fourfinanceit.core.services.loans;

import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Created by Anna on 06.02.2016.
 */
public class LoanValidatorImplTest {

    private LoanValidator validator = new LoanValidatorImpl();


    @Test
    public void validateShouldFailIfTermIsNegative() {
        validateShouldFail(-5, new BigDecimal("100"), "Loan term must be between 10 and 30 days");
    }

    @Test
    public void validateShouldFailIfTermIsPositiveButLessMin() {
        validateShouldFail(5, new BigDecimal("100"), "Loan term must be between 10 and 30 days");
    }

    @Test
    public void validateShouldFailIfTermIsMoreMax() {
        validateShouldFail(40, new BigDecimal("100"), "Loan term must be between 10 and 30 days");
    }

    @Test
    public void validateShouldFailIfAmountIsNegative() {
        validateShouldFail(20, new BigDecimal("-5"), "Loan amount must be between 50 and 425 euro");
    }

    @Test
    public void validateShouldFailIfAmountIsPositiveButLessMin() {
        validateShouldFail(20, new BigDecimal("10"), "Loan amount must be between 50 and 425 euro");
    }

    @Test
    public void validateShouldFailIfAmountIsMoreMax() {
        validateShouldFail(20, new BigDecimal("500"), "Loan amount must be between 50 and 425 euro");
    }

    private void validateShouldFail(int term,
                                    BigDecimal amount,
                                    String errorMessage) {
        try {
            validator.validate(term, amount);
            fail();
        } catch (Exception e) {
            assertThat(e.getMessage(), is(errorMessage));
        }
    }

}
