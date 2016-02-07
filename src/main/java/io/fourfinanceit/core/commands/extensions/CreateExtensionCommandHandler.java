package io.fourfinanceit.core.commands.extensions;

import io.fourfinanceit.core.domain.extension.Extension;
import io.fourfinanceit.core.dto.extension.ExtensionDTO;
import io.fourfinanceit.core.services.DomainCommandHandler;
import io.fourfinanceit.core.services.extensions.ExtensionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Anna on 07.02.2016.
 */

@Component
public class CreateExtensionCommandHandler implements DomainCommandHandler<CreateExtensionCommand, CreateExtensionResult> {

    @Autowired
    private ExtensionFactory extensionFactory;
    @Autowired  private ExtensionConverter extensionConverter;


    @Override
    public CreateExtensionResult execute(CreateExtensionCommand command) {
        Extension extension = extensionFactory.create(
                command.getWeeksCount(),
                command.getLoanId()
        );
        ExtensionDTO extensionDTO = extensionConverter.convert(extension);
        return new CreateExtensionResult(extensionDTO);
    }

    @Override
    public Class getCommandType() {
        return CreateExtensionCommand.class;
    }

}
