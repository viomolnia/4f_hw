package io.fourfinanceit.core.services.extensions;

import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by Anna on 08.02.2016.
 */

@Component

public class ExtensionValidatorImpl implements ExtensionValidator{

    @Override
    public void validate(int weeksCount) {
        checkArgument(weeksCount > 0, "Loan extension term must be at least one week");

    }
}
