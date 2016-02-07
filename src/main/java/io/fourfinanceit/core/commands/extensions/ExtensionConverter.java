package io.fourfinanceit.core.commands.extensions;

import io.fourfinanceit.core.domain.extension.Extension;
import io.fourfinanceit.core.dto.extension.ExtensionDTO;
import org.springframework.stereotype.Component;
import static io.fourfinanceit.core.dto.extension.ExtensionDTOBuilder.createExtensionDTO;

/**
 * Created by Anna on 07.02.2016.
 */

@Component
public class ExtensionConverter {
    public ExtensionDTO convert(Extension extension) {
        return createExtensionDTO()
                .withId(extension.getExtensionId())
                .withWeeksCount(extension.getWeeksCount())
                .withDateTaken(extension.getDateTaken())
                .withDateReturn(extension.getDateReturn())
                .withCost(extension.getCost())
                .withLoan(extension.getLoan())
                .build();
    }
}
