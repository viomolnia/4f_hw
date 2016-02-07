package io.fourfinanceit.core.dto.extension;

import io.fourfinanceit.core.commands.loans.LoanConverter;
import io.fourfinanceit.core.domain.loan.Loan;
import io.fourfinanceit.core.dto.loan.LoanDTO;

import java.math.BigDecimal;

/**
 * Created by Anna on 07.02.2016.
 */
public class ExtensionDTOBuilder {

    private Long extensionId;

    private int weeksCount;

    private String dateTaken;

    private String dateReturn;

    private BigDecimal cost;

    private Loan loan;

    private LoanDTO loanDTO;

    private Long loanId;

    public static ExtensionDTOBuilder createExtensionDTO() {
        return new ExtensionDTOBuilder();
    }

    public ExtensionDTO build() {
        ExtensionDTO extension = new ExtensionDTO();

        extension.setExtensionId(extensionId);
        extension.setWeeksCount(weeksCount);
        extension.setDateTaken(dateTaken);
        extension.setDateReturn(dateReturn);
        extension.setCost(cost);

        if(loan != null){
            extension.setLoanId(loan.getLoanId());
            extension.setLoanDTO(new LoanConverter().convert(loan));
        } else {
            extension.setLoanId(loanId);
            extension.setLoanDTO(loanDTO);
        }

        return extension;
    }

    public ExtensionDTOBuilder withId(Long id) {
        this.extensionId = id;
        return this;
    }

    public ExtensionDTOBuilder withWeeksCount(int weeksCount) {
        this.weeksCount = weeksCount;
        return this;
    }

    public ExtensionDTOBuilder withDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
        return this;
    }

    public ExtensionDTOBuilder withDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
        return this;
    }

    public ExtensionDTOBuilder withCost(BigDecimal cost) {
        this.cost = cost;
        return this;
    }

    public ExtensionDTOBuilder withLoan(Loan loan) {
        this.loan = loan;
        return this;
    }

    public ExtensionDTOBuilder withLoanDTO(LoanDTO loanDTO) {
        this.loanDTO = loanDTO;
        return this;
    }
}
