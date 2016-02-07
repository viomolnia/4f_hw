package io.fourfinanceit.core.dto.loan;

import io.fourfinanceit.core.dto.user.UserDTO;

import java.math.BigDecimal;

/**
 * Created by Anna on 03.02.2016.
 */
public class LoanDTO {

    private Long loanId;

    private int term;

    private BigDecimal amount;

    private BigDecimal indexedAmount;

    private BigDecimal interest;

    private String dateTaken;

    private String dateReturn;

    private UserDTO userDTO;

    private Long userId;

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
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

    public String getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public String getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
