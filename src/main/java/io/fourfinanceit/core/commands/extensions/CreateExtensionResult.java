package io.fourfinanceit.core.commands.extensions;

import io.fourfinanceit.core.commands.DomainCommandResult;
import io.fourfinanceit.core.dto.extension.ExtensionDTO;

/**
 * Created by Anna on 07.02.2016.
 */
public class CreateExtensionResult implements DomainCommandResult {

    private ExtensionDTO extension;

    public CreateExtensionResult(ExtensionDTO extension) {
        this.extension = extension;
    }

    public ExtensionDTO getExtension() {
        return extension;
    }

}
