package io.fourfinanceit.core.services.loans;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.google.common.base.Preconditions.checkArgument;
/**
 * Created by Anna on 03.02.2016.
 */

@Component
public class LoanValidatorImpl implements LoanValidator {

    @Override
    public void validate(int term, BigDecimal amount) {

        validateTerm(term);
        validateAmount(amount);
    }

    private void validateTerm(int term) {
        checkArgument(term >= 10 && term <= 30, "Loan term must be between 10 and 30 days");
    }

    private void validateAmount(BigDecimal amount) {
        BigDecimal minLimit = new BigDecimal("50");
        BigDecimal maxLimit = new BigDecimal("425");
        checkArgument(amount.compareTo(minLimit)>0 && amount.compareTo(maxLimit)<0, "Loan amount must be between 50 and 425 euro");
    }

}
