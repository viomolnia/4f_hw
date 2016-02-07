package io.fourfinanceit.core.commands.users;

/**
 * Created by Anna on 03.02.2016.
 */

import io.fourfinanceit.core.domain.user.User;
import io.fourfinanceit.core.dto.user.UserDTO;
import io.fourfinanceit.core.services.DomainCommandHandler;
import io.fourfinanceit.core.services.users.UserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
class CreateUserCommandHandler
        implements DomainCommandHandler<CreateUserCommand, CreateUserResult> {

    @Autowired private UserFactory userFactory;
    @Autowired private UserConverter userConverter;



    @Override
    public CreateUserResult execute(CreateUserCommand command) {
        User user = userFactory.create(
                command.getName(),
                command.getSurname(),
                command.getPersonCode()
        );
        UserDTO userDTO = userConverter.convert(user);
        return new CreateUserResult(userDTO);
    }

    @Override
    public Class getCommandType() {
        return CreateUserCommand.class;
    }

}

