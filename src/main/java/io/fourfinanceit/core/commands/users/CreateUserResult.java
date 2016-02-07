package io.fourfinanceit.core.commands.users;

import io.fourfinanceit.core.commands.DomainCommandResult;
import io.fourfinanceit.core.dto.user.UserDTO;

/**
 * Created by Anna on 03.02.2016.
 */
public class CreateUserResult implements DomainCommandResult {

    private UserDTO user;

    public CreateUserResult(UserDTO user) {
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }


}
