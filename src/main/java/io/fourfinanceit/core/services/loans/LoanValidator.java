package io.fourfinanceit.core.services.loans;

import java.math.BigDecimal;

/**
 * Created by Anna on 03.02.2016.
 */
public interface LoanValidator {

    void validate(int term, BigDecimal amount, Long userId, int hour);

}
