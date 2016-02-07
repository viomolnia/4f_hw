package io.fourfinanceit.core.commands.loans;
import io.fourfinanceit.core.commands.DomainCommand;

import java.math.BigDecimal;

/**
 * Created by Anna on 03.02.2016.
 */
public class CreateLoanCommand implements DomainCommand<CreateLoanResult>{

    private int term;

    private BigDecimal amount;

    private BigDecimal indexedAmount;

    private double interest;

    private String dateTaken;

    private String dateReturn;

    private Long userId;

    public CreateLoanCommand(int term, BigDecimal amount, Long userId) {
        super();
        this.term = term;
        this.amount = amount;
        this.indexedAmount = indexedAmount;
        this.interest = interest;
        this.dateTaken = dateTaken;
        this.dateReturn = dateReturn;
        this.userId = userId;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getIndexedAmount() {
        return indexedAmount;
    }

    public void setIndexedAmount(BigDecimal indexedAmount) {
        this.indexedAmount = indexedAmount;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public String getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }




}