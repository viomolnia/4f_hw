package io.fourfinanceit.core.domain.loan;

import io.fourfinanceit.core.domain.extension.Extension;
import io.fourfinanceit.core.domain.user.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by Anna on 03.02.2016.
 */

@Entity
@Table(name="loans")
public class Loan {

    @Id
    @Column(name="loan_id",columnDefinition = "int(11)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loanId;

    @Column(name="term", columnDefinition = "INTEGER")
    private int term;

    @Column(name="amount", columnDefinition = "NUMERIC")
    private BigDecimal amount;

    @Column(name="indexed_amount", columnDefinition = "NUMERIC")
    private BigDecimal indexedAmount;

    @Column(name="interest", columnDefinition = "NUMERIC")
    private BigDecimal interest;

    @Column(name="date_taken", columnDefinition = "CHAR(50)")
    private String dateTaken;

    @Column(name="date_return", columnDefinition = "CHAR(50)")
    private String dateReturn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "loan", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Extension> extensions;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(String dateReturn) {
        this.dateReturn = dateReturn;
    }

    public Set<Extension> getExtensions() {
        return extensions;
    }

    public void setExtensions(Set<Extension> extensions) {
        this.extensions = extensions;
    }

}
