package io.fourfinanceit.core.services.loans;

import io.fourfinanceit.core.database.LoanDAO;
import io.fourfinanceit.core.domain.loan.Loan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.inOrder;

/**
 * Created by Anna on 06.02.2016.
 */

@RunWith(MockitoJUnitRunner.class)

public class LoanServiceImplTest {

    @Mock    private LoanDAO loanDAO;

    @InjectMocks
    private LoanFactory loanFactory = new LoanFactoryImpl();
    @InjectMocks    private LoanService loanService = new LoanServiceImpl();

    @Test
    public void getShouldReturnLoan() {

        Loan gotLoan = loanService.get(1L);
        InOrder inOrder = inOrder(loanDAO);
        inOrder.verify(loanDAO).getRequired(1L);
    }

}
