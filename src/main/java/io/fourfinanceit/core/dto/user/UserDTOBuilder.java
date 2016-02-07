package io.fourfinanceit.core.dto.user;

/**
 * Created by Anna on 03.02.2016.
 */
public class UserDTOBuilder {

    private Long userId;

    private String name;

    private String surname;

    private String personCode;


    public static UserDTOBuilder createUserDTO() {
        return new UserDTOBuilder();
    }

    public UserDTO build(){
        UserDTO user = new UserDTO();

        user.setUserId(userId);
        user.setName(name);
        user.setSurname(surname);
        user.setPersonCode(personCode);

        return user;
    }

    public UserDTOBuilder withId(Long id) {
        this.userId = id;
        return this;
    }

    public UserDTOBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserDTOBuilder withSurname (String surname) {
        this.surname = surname;
        return this;
    }

    public UserDTOBuilder withPersonCode(String personCode) {
        this.personCode = personCode;
        return this;
    }

}
