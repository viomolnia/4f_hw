package io.fourfinanceit.core.services.users;

import org.springframework.stereotype.Component;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * Created by Anna on 03.02.2016.
 */

@Component
public class UserValidatorImpl implements UserValidator {

    @Override
    public void validate(String name, String surname) {
        validateLogin(name);
        validatePassword(surname);
    }

    private void validateLogin(String name) {
        checkNotNull(name, "User name must not be null");
        checkArgument(!isBlank(name), "User name must not be empty");
    }

    private void validatePassword(String surname) {
        checkNotNull(surname, "User surname must not be null");
        checkArgument(!isBlank(surname), "User surname must not be empty");
    }
}