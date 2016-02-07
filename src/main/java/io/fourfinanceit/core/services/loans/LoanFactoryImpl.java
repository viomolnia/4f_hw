package io.fourfinanceit.core.services.loans;

import io.fourfinanceit.core.database.LoanDAO;
import io.fourfinanceit.core.database.UserDAO;
import io.fourfinanceit.core.domain.loan.Loan;
import io.fourfinanceit.core.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static io.fourfinanceit.core.domain.loan.LoanBuilder.createLoan;

/**
 * Created by Anna on 03.02.2016.
 */

@Component
public class LoanFactoryImpl implements LoanFactory {

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    @Autowired private LoanDAO loanDAO;
    @Autowired private LoanValidator loanValidator;
    @Autowired private UserDAO userDAO;

    @Override
    public Loan create(int term, BigDecimal amount, Long userId) {

        loanValidator.validate(term, amount);

        //selecting interest and counting indexed amount
        BigDecimal interest = selectInterest(amount);
        BigDecimal indexedAmount = amount.multiply(interest);

        //getting date of application
        Calendar calendarDateTaken = Calendar.getInstance();
        String dateTaken = dateFormat.format(calendarDateTaken.getTime());

        //getting date of returning
        calendarDateTaken.add(Calendar.DATE, term);
        String dateReturn = dateFormat.format(calendarDateTaken.getTime());

        User userFromDB = userDAO.getById(userId);

        if(userFromDB != null){
            Loan loan = createLoan()
                    .withTerm(term)
                    .withAmount(amount)
                    .withIndexedAmount(indexedAmount)
                    .withInterest(interest)
                    .withDateTaken(dateTaken)
                    .withDateReturn(dateReturn)
                    .withUser(userFromDB)
                    .build();
            loanDAO.create(loan);
            return loan;
        } else {
            return null;
        }
    }

    private static BigDecimal selectInterest(BigDecimal reqAmount){

        //if requested amount is less than 300
        BigDecimal interest = new BigDecimal("1");

        //if requested amount equals or more than 300
        if(reqAmount.compareTo(new BigDecimal("300")) >= 0){
            interest = new BigDecimal("1.04");
        }

        return interest;
    }

}
