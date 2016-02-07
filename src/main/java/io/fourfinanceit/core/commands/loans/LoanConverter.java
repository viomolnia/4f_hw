package io.fourfinanceit.core.commands.loans;

import org.springframework.stereotype.Component;

import io.fourfinanceit.core.domain.loan.Loan;
import io.fourfinanceit.core.dto.loan.LoanDTO;
import static io.fourfinanceit.core.dto.loan.LoanDTOBuilder.createLoanDTO;


/**
 * Created by Anna on 03.02.2016.
 */

@Component
public class LoanConverter {

    public LoanDTO convert(Loan loan) {
        return createLoanDTO()
                .withId(loan.getLoanId())
                .withTerm(loan.getTerm())
                .withAmount(loan.getAmount())
                .withIndexedAmount(loan.getIndexedAmount())
                .withInterest(loan.getInterest())
                .withDateTaken(loan.getDateTaken())
                .withDateReturn(loan.getDateReturn())
                .withUser(loan.getUser())
                .build();
    }

}