package io.fourfinanceit.core.services.loans;

import io.fourfinanceit.core.domain.loan.Loan;

import java.math.BigDecimal;

/**
 * Created by Anna on 03.02.2016.
 */
public interface LoanFactory {

    //creates loan for user with definite term and amount
    Loan create(int term, BigDecimal amount, Long userId);


}
