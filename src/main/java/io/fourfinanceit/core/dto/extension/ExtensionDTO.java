package io.fourfinanceit.core.dto.extension;

import io.fourfinanceit.core.dto.loan.LoanDTO;

import java.math.BigDecimal;

/**
 * Created by Anna on 07.02.2016.
 */
public class ExtensionDTO {

    private Long extensionId;

    private int weeksCount;

    private String dateTaken;

    private String dateReturn;

    private BigDecimal cost;

    private LoanDTO loanDTO;

    private Long loanId;

    public Long getExtensionId() {
        return extensionId;
    }

    public void setExtensionId(Long extensionId) {
        this.extensionId = extensionId;
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

    public LoanDTO getLoanDTO() {
        return loanDTO;
    }

    public void setLoanDTO(LoanDTO loanDTO) {
        this.loanDTO = loanDTO;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
