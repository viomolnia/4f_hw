package io.fourfinanceit.core.domain.user;

import io.fourfinanceit.core.domain.loan.Loan;

import java.util.List;

/**
 * Created by Anna on 03.02.2016.
 */
public class UserBuilder {

    private Long userId;

    private String name;

    private String surname;

    private String personCode;

    private List<Loan> loans;

    public static UserBuilder createUser() {
        return new UserBuilder();
    }

    public User build(){
        User user = new User();

        user.setUserId(userId);
        user.setName(name);
        user.setSurname(surname);
        user.setPersonCode(personCode);

        return user;
    }

    public UserBuilder withId(Long userId) {
        this.userId = userId;
        return this;
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withSurname (String surname) {
        this.surname = surname;
        return this;
    }

    public UserBuilder withPersonCode(String personCode) {
        this.personCode = personCode;
        return this;
    }
}
