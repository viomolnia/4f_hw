package io.fourfinanceit.core.services;

import io.fourfinanceit.core.commands.DomainCommand;
import io.fourfinanceit.core.commands.DomainCommandResult;

/**
 * Created by Anna on 03.02.2016.
 */

public interface CommandExecutor {

    <T extends DomainCommandResult> T execute(DomainCommand<T> domainCommand);

}
