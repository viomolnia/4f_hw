package io.fourfinanceit.core.commands.loans;

import io.fourfinanceit.core.commands.DomainCommand;

/**
 * Created by Anna on 03.02.2016.
 */
public class GetLoanCommand implements DomainCommand<GetLoanResult> {

    private Long loanId;

    public GetLoanCommand(Long loanId) {
        this.loanId = loanId;
    }

    public Long getLoanId() {
        return loanId;
    }

}