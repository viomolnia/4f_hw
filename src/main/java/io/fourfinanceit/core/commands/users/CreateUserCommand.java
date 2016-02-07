package io.fourfinanceit.core.commands.users;

import io.fourfinanceit.core.commands.DomainCommand;

/**
 * Created by Anna on 03.02.2016.
 */
public class CreateUserCommand implements DomainCommand<CreateUserResult> {

    private String name;
    private String surname;
    private String personCode;

    public CreateUserCommand(
                             String name,
                             String surname,
                             String personCode) {

        this.name = name;
        this.surname = surname;
        this.personCode = personCode;
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
}
