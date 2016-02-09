package io.fourfinanceit.core.domain.extension;

/**
 * Created by Anna on 05.02.2016.
 */
import io.fourfinanceit.core.domain.loan.Loan;

import javax.persistence.*;
import java.math.BigDecimal;

//Entity for storing loan extension

@Entity
@Table(name="extensions")
public class Extension {

    @Id
    @Column(name="extension_id",columnDefinition = "int(11)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long extensionId; //id generated while adding new instance

    @Column(name="weeks_count", columnDefinition = "INTEGER")
    private int weeksCount=1; //default extension term in weeks

    @Column(name="date_ext_taken", columnDefinition = "CHAR(50)")
    private String dateTaken; //date and time, when apply for extension was made

    @Column(name="date_ext_return", columnDefinition = "CHAR(50)")
    private String dateReturn;// date and time, when user has to return loan indexed amount and extension cost

    @Column(name="cost", columnDefinition = "NUMERIC")
    private BigDecimal cost; //cost of extending definite loan according to extension term

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loan_id")
    private Loan loan; //loan, that is being extended

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

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
