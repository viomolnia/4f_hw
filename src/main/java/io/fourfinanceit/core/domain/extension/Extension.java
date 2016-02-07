package io.fourfinanceit.core.domain.extension;

/**
 * Created by Anna on 05.02.2016.
 */
import io.fourfinanceit.core.domain.loan.Loan;

import javax.persistence.*;

@Entity
@Table(name="extensions")
public class Extension {

    @Id
    @Column(name="extension_id",columnDefinition = "int(11)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long extensionId;

    @Column(name="term", columnDefinition = "INTEGER")
    private int term;

    @Column(name="date_ext_taken", columnDefinition = "CHAR(50)")
    private String dateTaken;

    @Column(name="date_ext_return", columnDefinition = "CHAR(50)")
    private String dateReturn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "loan_id")
    private Loan loan;

    public Long getExtensionId() {
        return extensionId;
    }

    public void setExtensionId(Long extensionId) {
        this.extensionId = extensionId;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
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
}
