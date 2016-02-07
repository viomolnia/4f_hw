package io.fourfinanceit.core.commands.loans;

/**
 * Created by Anna on 03.02.2016.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.fourfinanceit.core.domain.loan.Loan;
import io.fourfinanceit.core.dto.loan.LoanDTO;
import io.fourfinanceit.core.services.DomainCommandHandler;
import io.fourfinanceit.core.services.loans.LoanFactory;

/**
 * Created by Anna on 03.02.2016.
 */

@Component
public class CreateLoanCommandHandler implements DomainCommandHandler<CreateLoanCommand, CreateLoanResult> {

    @Autowired  private LoanFactory loanFactory;
    @Autowired  private LoanConverter loanConverter;


    @Override
    public CreateLoanResult execute(CreateLoanCommand command) {
        Loan loan = loanFactory.create(
                command.getTerm(),
                command.getAmount(),
                command.getUserId()
        );
        LoanDTO loanDTO = loanConverter.convert(loan);
        return new CreateLoanResult(loanDTO);
    }

    @Override
    public Class getCommandType() {
        return CreateLoanCommand.class;
    }

}
