package io.fourfinanceit.core.database;

import io.fourfinanceit.core.domain.attempt.Attempt;

/**
 * Created by Anna on 07.02.2016.
 */
public interface AttemptDAO extends CRUDOperationDAO<Attempt, Long> {
    Attempt getTimesByIp(String ip);

}
