package io.fourfinanceit.core.services;

import io.fourfinanceit.core.commands.DomainCommand;
import io.fourfinanceit.core.commands.DomainCommandResult;

/**
 * Created by Anna on 03.02.2016.
 */

public interface DomainCommandHandler<C extends DomainCommand, R extends DomainCommandResult> {

    R execute(C command);

    Class getCommandType();

}