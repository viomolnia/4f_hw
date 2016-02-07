package io.fourfinanceit.core.commands.loans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.fourfinanceit.core.domain.loan.Loan;
import io.fourfinanceit.core.dto.loan.LoanDTO;
import io.fourfinanceit.core.services.DomainCommandHandler;
import io.fourfinanceit.core.services.loans.LoanService;

/**
 * Created by Anna on 03.02.2016.
 */
@Component
public class GetLoanCommandHandler implements DomainCommandHandler<GetLoanCommand, GetLoanResult> {

    @Autowired
    private LoanService loanService;
    @Autowired  private LoanConverter loanConverter;



    @Override
    public GetLoanResult execute(GetLoanCommand command) {
        Loan loan = loanService.get(command.getLoanId());
        LoanDTO loanDTO = loanConverter.convert(loan);
        return new GetLoanResult(loanDTO);
    }

    @Override
    public Class getCommandType() {
        return GetLoanCommand.class;
    }

}
