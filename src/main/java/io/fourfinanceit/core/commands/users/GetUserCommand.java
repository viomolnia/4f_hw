package io.fourfinanceit.core.commands.users;

import io.fourfinanceit.core.commands.DomainCommand;

/**
 * Created by Anna on 03.02.2016.
 */
public class GetUserCommand implements DomainCommand<GetUserResult> {

    private Long userId;

    public GetUserCommand(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }


}
