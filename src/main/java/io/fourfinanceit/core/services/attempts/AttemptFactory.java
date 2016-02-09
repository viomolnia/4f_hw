package io.fourfinanceit.core.services.attempts;

import io.fourfinanceit.core.domain.attempt.Attempt;

/**
 * Created by Anna on 07.02.2016.
 */
public interface AttemptFactory {

    //Creates a record of attempt to call creating a loan
    Attempt create(String ip);

}
