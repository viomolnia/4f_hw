package io.fourfinanceit.core.commands.loans;

import io.fourfinanceit.core.commands.DomainCommandResult;
import io.fourfinanceit.core.dto.loan.LoanDTO;

/**
 * Created by Anna on 03.02.2016.
 */
public class CreateLoanResult implements DomainCommandResult {

    private LoanDTO loan;

    public CreateLoanResult(LoanDTO loan) {
        this.loan = loan;
    }

    public LoanDTO getLoan() {
        return loan;
    }

}

