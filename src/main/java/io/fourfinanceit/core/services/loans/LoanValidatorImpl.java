package io.fourfinanceit.core.services.loans;

import io.fourfinanceit.config.Constants;
import io.fourfinanceit.core.services.users.UserService;
import io.fourfinanceit.core.services.users.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static com.google.common.base.Preconditions.checkArgument;
/**
 * Created by Anna on 03.02.2016.
 */

@Component
public class LoanValidatorImpl implements LoanValidator {


    @Autowired
    private UserService userService = new UserServiceImpl();

    @Override
    public void validate(int term, BigDecimal amount, Long userId, int hour) {
        validateTerm(term);
        validateAmount(amount);
        validateAmountAndUser(amount, userId);
        validateCalAmount(hour, amount);
    }

    private void validateTerm(int term) {
        int minTerm = Integer.parseInt(Constants.MinTerm.getValue());
        int maxTerm = Integer.parseInt(Constants.MaxTerm.getValue());
        checkArgument(term >= minTerm && term <= maxTerm, "Loan term must be between 10 and 30 days");
    }

    private void validateAmount(BigDecimal amount) {
        BigDecimal minAmount = new BigDecimal(Constants.MinAmount.getValue());
        BigDecimal maxAmount2 = new BigDecimal(Constants.MaxAmount2.getValue());
        checkArgument(amount.compareTo(maxAmount2)<=0 && amount.compareTo(minAmount)>=0, "Loan amount must be between 50 and 425 euro");
    }

    private void validateAmountAndUser(BigDecimal amount, Long userId) {
        BigDecimal maxAmount1 = new BigDecimal(Constants.MaxAmount1.getValue());
        int userLoansCount = userService.get(userId).getLoans().size();
        checkArgument(amount.compareTo(maxAmount1)<=0 || userLoansCount >=1, "Loan with amount more than 300 euro are allowed from second apply");
    }

    private void validateCalAmount(int hour, BigDecimal amount) {
        BigDecimal maxAmount2 = new BigDecimal(Constants.MaxAmount2.getValue());
        checkArgument(amount.compareTo(maxAmount2)<=0 || hour > 6, "Loans with max amount are not allowed after 00:00");
    }

}
