package io.fourfinanceit.core.domain.loan;

import io.fourfinanceit.core.domain.user.User;

import java.math.BigDecimal;

/**
 * Created by Anna on 03.02.2016.
 */
public class LoanBuilder {

    private Long loanId;

    private int term;

    private BigDecimal amount;

    private BigDecimal indexedAmount;

    private BigDecimal interest;

    private String dateTaken;

    private String dateReturn;

    private User user;

    public static LoanBuilder createLoan() {
        return new LoanBuilder();
    }

    public Loan build(){
        Loan loan = new Loan();

        loan.setLoanId(loanId);
        loan.setTerm(term);
        loan.setAmount(amount);
        loan.setIndexedAmount(indexedAmount);
        loan.setDateReturn(dateReturn);
        loan.setInterest(interest);
        loan.setDateTaken(dateTaken);
        loan.setUser(user);
        return loan;
    }

    public LoanBuilder withId(Long id) {
        this.loanId = id;
        return this;
    }

    public LoanBuilder withTerm(int term) {
        this.term = term;
        return this;
    }

    public LoanBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public LoanBuilder withIndexedAmount(BigDecimal indexedAmount) {
        this.indexedAmount = indexedAmount;
        return this;
    }

    public LoanBuilder withDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
        return this;
    }

    public LoanBuilder withInterest(BigDecimal interest) {
        this.interest = interest;
        return this;
    }

    public LoanBuilder withDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
        return this;
    }

    public LoanBuilder withUser(User user) {
        this.user = user;
        return this;
    }
}
