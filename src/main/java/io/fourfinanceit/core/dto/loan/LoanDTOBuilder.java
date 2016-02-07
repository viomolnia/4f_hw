package io.fourfinanceit.core.dto.loan;

import io.fourfinanceit.core.commands.users.UserConverter;
import io.fourfinanceit.core.domain.user.User;
import io.fourfinanceit.core.dto.user.UserDTO;

import java.math.BigDecimal;

/**
 * Created by Anna on 03.02.2016.
 */
public class LoanDTOBuilder {

    private Long loanId;

    private int term;

    private BigDecimal amount;

    private BigDecimal indexedAmount;

    private BigDecimal interest;

    private String dateTaken;

    private String dateReturn;

    private User user;

    private UserDTO userDTO;

    private Long userId;

    public static LoanDTOBuilder createLoanDTO() {
        return new LoanDTOBuilder();
    }

    public LoanDTO build() {
        LoanDTO loan = new LoanDTO();

        loan.setLoanId(loanId);
        loan.setTerm(term);
        loan.setAmount(amount);
        loan.setIndexedAmount(indexedAmount);
        loan.setDateReturn(dateReturn);
        loan.setInterest(interest);
        loan.setDateTaken(dateTaken);

        if(user != null){
            loan.setUserId(user.getUserId());
            loan.setUserDTO(new UserConverter().convert(user));
        } else {
            loan.setUserId(userId);
            loan.setUserDTO(userDTO);
        }

        return loan;
    }

    public LoanDTOBuilder withId(Long id) {
        this.loanId = id;
        return this;
    }

    public LoanDTOBuilder withTerm(int term) {
        this.term = term;
        return this;
    }

    public LoanDTOBuilder withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public LoanDTOBuilder withIndexedAmount(BigDecimal indexedAmount) {
        this.indexedAmount = indexedAmount;
        return this;
    }

    public LoanDTOBuilder withDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
        return this;
    }

    public LoanDTOBuilder withInterest(BigDecimal interest) {
        this.interest = interest;
        return this;
    }

    public LoanDTOBuilder withDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
        return this;
    }

    public LoanDTOBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public LoanDTOBuilder withUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
        return this;
    }

}
