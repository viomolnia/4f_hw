package io.fourfinanceit.core.services.loans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.fourfinanceit.core.database.LoanDAO;
import io.fourfinanceit.core.domain.loan.Loan;

/**
 * Created by Anna on 03.02.2016.
 */

@Component
public class LoanServiceImpl implements LoanService {

    @Autowired private LoanDAO loanDAO;

    @Override
    public Loan get(Long loanId) {
        return loanDAO.getRequired(loanId);
    }


}
