package io.fourfinanceit.core.services.extensions;

import io.fourfinanceit.core.database.ExtensionDAO;
import io.fourfinanceit.core.database.LoanDAO;
import io.fourfinanceit.core.domain.extension.Extension;
import io.fourfinanceit.core.domain.loan.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;

import static io.fourfinanceit.core.domain.extension.ExtensionBuilder.createExtension;

/**
 * Created by Anna on 07.02.2016.
 */

@Component
public class ExtensionFactoryImpl implements ExtensionFactory{

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");

    @Autowired
    private ExtensionDAO extensionDAO;
    @Autowired private LoanDAO loanDAO;

    @Override
    public Extension create(int weeksCount, Long loanId) {
        Loan loan = loanDAO.getById(loanId);
        //getting date of return of loan
        Calendar calendarDateReturnLoan = Calendar.getInstance();

        //getting cost of extension
        BigDecimal cost = loan.getIndexedAmount().multiply(new BigDecimal("0.015")).multiply(new BigDecimal(weeksCount));

        //if there were not any extensions yet: date of returning loan is extended
        if(loan.getExtensions() == null || loan.getExtensions().size() == 0){
            try {
                calendarDateReturnLoan.setTime(dateFormat.parse(loan.getDateReturn()));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        //if loan has been extended: date of returning loan is taken from latest extension return date
        } else {
            try {
                Extension lastExtension = (Extension) getLastElement(loan.getExtensions());
                calendarDateReturnLoan.setTime(dateFormat.parse(lastExtension.getDateReturn()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //getting date of extension application
        Calendar calendarDateExtensionTaken = Calendar.getInstance();
        String dateTaken = dateFormat.format(calendarDateExtensionTaken.getTime());

        //getting date of returning by extension
        calendarDateReturnLoan.add(Calendar.DATE, weeksCount*7);
        String dateReturn = dateFormat.format(calendarDateReturnLoan.getTime());

        Loan loanFromDB = loanDAO.getById(loanId);

        if(loanFromDB != null){
            Extension extension = createExtension()
                    .withWeeksCount(weeksCount)
                    .withDateTaken(dateTaken)
                    .withDateReturn(dateReturn)
                    .withCost(cost)
                    .withLoan(loanFromDB)
                    .build();
            extensionDAO.create(extension);
            return extension;
        } else {
            return null;
        }
    }

    public static Object getLastElement(final Collection c) {
        final Iterator itr = c.iterator();
        Object lastElement = itr.next();
        while(itr.hasNext()) {
            lastElement=itr.next();
        }
        return lastElement;
    }
}
