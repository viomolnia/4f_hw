package io.fourfinanceit.core.commands.users;

/**
 * Created by Anna on 03.02.2016.
 */

import io.fourfinanceit.core.domain.user.User;
import io.fourfinanceit.core.dto.user.UserDTO;
import org.springframework.stereotype.Component;
import static io.fourfinanceit.core.dto.user.UserDTOBuilder.createUserDTO;


@Component
public class UserConverter {

    public UserDTO convert(User user) {
        return createUserDTO()
                .withId(user.getUserId())
                .withName(user.getName())
                .withSurname(user.getSurname())
                .withPersonCode(user.getPersonCode())
                .build();
    }



}
