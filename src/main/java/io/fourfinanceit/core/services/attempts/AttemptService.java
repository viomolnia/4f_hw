package io.fourfinanceit.core.services.attempts;

import io.fourfinanceit.core.domain.attempt.Attempt;

/**
 * Created by Anna on 07.02.2016.
 */
public interface AttemptService {

    Attempt get(Long attemptId);

    Attempt get(String ip);

    Attempt updateTimes(String ip);

    void delete(String ip);
}
