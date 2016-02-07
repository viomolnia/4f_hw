package io.fourfinanceit.core.commands.users;

/**
 * Created by Anna on 03.02.2016.
 */

import io.fourfinanceit.core.domain.user.User;
import io.fourfinanceit.core.dto.user.UserDTO;
import io.fourfinanceit.core.services.DomainCommandHandler;
import io.fourfinanceit.core.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class GetUserCommandHandler
        implements DomainCommandHandler<GetUserCommand, GetUserResult> {

    @Autowired private UserService userService;
    @Autowired private UserConverter userConverter;



    @Override
    public GetUserResult execute(GetUserCommand command) {
        User user = userService.get(command.getUserId());
        UserDTO userDTO = userConverter.convert(user);
        return new GetUserResult(userDTO);
    }

    @Override
    public Class getCommandType() {
        return GetUserCommand.class;
    }

}

