package io.fourfinanceit.core.domain.extension;

import io.fourfinanceit.core.domain.loan.Loan;

import java.math.BigDecimal;

/**
 * Created by Anna on 05.02.2016.
 */
public class ExtensionBuilder {

    private Long extensionId;

    private int weeksCount;

    private String dateTaken;

    private String dateReturn;

    private Loan loan;

    private BigDecimal cost;

    public static ExtensionBuilder createExtension() {
        return new ExtensionBuilder();
    }

    public Extension build() {
        Extension extension = new Extension();

        extension.setExtensionId(extensionId);
        extension.setWeeksCount(weeksCount);
        extension.setDateTaken(dateTaken);
        extension.setDateReturn(dateReturn);
        extension.setCost(cost);
        extension.setLoan(loan);
        return extension;
    }

    public ExtensionBuilder withId(Long id) {
        this.extensionId = id;
        return this;
    }

    public ExtensionBuilder withWeeksCount(int weeksCount) {
        this.weeksCount = weeksCount;
        return this;
    }

    public ExtensionBuilder withDateTaken(String dateTaken) {
        this.dateTaken = dateTaken;
        return this;
    }

    public ExtensionBuilder withDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
        return this;
    }

    public ExtensionBuilder withCost(BigDecimal cost) {
        this.cost = cost;
        return this;
    }

    public ExtensionBuilder withLoan(Loan loan) {
        this.loan = loan;
        return this;
    }

}
