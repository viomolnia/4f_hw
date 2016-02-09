package io.fourfinanceit.core.domain.user;

/**
 * Created by Anna on 03.02.2016.
 */

import io.fourfinanceit.core.domain.loan.Loan;

import javax.persistence.*;
import java.util.Set;

//Entity for storing user, that will take loans

@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name="user_id", columnDefinition = "int(11)")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name="name", columnDefinition = "CHAR(50)")
    private String name;

    @Column(name="surname", columnDefinition = "CHAR(50)")
    private String surname;

    @Column(name="person_code", columnDefinition = "CHAR(50)")
    private String personCode;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Set<Loan> loans;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public Set<Loan> getLoans() {
        return loans;
    }

    public void setLoans(Set<Loan> loans) {
        this.loans = loans;
    }
}
