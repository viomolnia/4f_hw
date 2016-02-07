package io.fourfinanceit.core.services.services;

import io.fourfinanceit.core.domain.attempt.Attempt;

/**
 * Created by Anna on 07.02.2016.
 */
public interface AttemptFactory {

    Attempt create(String ip);

}
