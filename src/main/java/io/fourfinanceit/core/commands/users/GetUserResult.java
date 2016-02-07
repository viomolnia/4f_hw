package io.fourfinanceit.core.commands.users;

import io.fourfinanceit.core.commands.DomainCommandResult;
import io.fourfinanceit.core.dto.user.UserDTO;

/**
 * Created by Anna on 03.02.2016.
 */
public class GetUserResult implements DomainCommandResult {

    private UserDTO user;

    public GetUserResult(UserDTO user) {
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }

}