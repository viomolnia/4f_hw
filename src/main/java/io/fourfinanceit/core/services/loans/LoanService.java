package io.fourfinanceit.core.services.loans;

import io.fourfinanceit.core.domain.loan.Loan;

/**
 * Created by Anna on 03.02.2016.
 */
public interface LoanService {
    Loan get(Long loanId);
}
