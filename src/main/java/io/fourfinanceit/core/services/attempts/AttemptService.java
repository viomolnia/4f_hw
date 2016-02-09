package io.fourfinanceit.core.services.attempts;

import io.fourfinanceit.core.domain.attempt.Attempt;

/**
 * Created by Anna on 07.02.2016.
 */
public interface AttemptService {

    // get attempt by id
    Attempt get(Long attemptId);

    // get attempt by calling ip
    Attempt get(String ip);

    // get update times of calling loan creation from single IP
    Attempt updateTimes(String ip);

    // delete a record about calling loan creation from single ip, if date has expired
    void delete(String ip);
}
