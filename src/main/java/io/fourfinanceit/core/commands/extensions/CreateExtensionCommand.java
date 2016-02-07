package io.fourfinanceit.core.commands.extensions;

import io.fourfinanceit.core.commands.DomainCommand;

import java.math.BigDecimal;

/**
 * Created by Anna on 07.02.2016.
 */
public class CreateExtensionCommand implements DomainCommand<CreateExtensionResult> {

    private int weeksCount;

    private String dateTaken;

    private String dateReturn;

    private BigDecimal cost;

    private Long loanId;

    public CreateExtensionCommand(int weeksCount, Long loanId) {
        this.weeksCount = weeksCount;
        this.dateTaken = dateTaken;
        this.dateReturn = dateReturn;
        this.cost = cost;
        this.loanId = loanId;
    }

    public int getWeeksCount() {
        return weeksCount;
    }

    public void setWeeksCount(int weeksCount) {
        this.weeksCount = weeksCount;
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

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }
}
