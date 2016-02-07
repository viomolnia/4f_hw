package io.fourfinanceit.core.database.loans;

import org.springframework.stereotype.Component;

import io.fourfinanceit.core.database.LoanDAO;
import io.fourfinanceit.core.domain.loan.Loan;

/**
 * Created by Anna on 03.02.2016.
 */
@Component
public class LoanDAOImpl extends CRUDOperationDAOImpl<Loan, Long> implements LoanDAO {

}
