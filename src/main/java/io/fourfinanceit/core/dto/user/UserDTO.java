package io.fourfinanceit.core.dto.user;

import java.io.Serializable;

/**
 * Created by Anna on 03.02.2016.
 */
public class UserDTO implements Serializable {

    private Long userId;

    private String name;

    private String surname;

    private String personCode;

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
}
