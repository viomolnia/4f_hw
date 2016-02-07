package io.fourfinanceit.core.services.attempts;

import io.fourfinanceit.core.database.AttemptDAO;
import io.fourfinanceit.core.domain.attempt.Attempt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;

/**
 * Created by Anna on 07.02.2016.
 */

@Component
public class AttemptServiceImpl implements AttemptService{

    @Autowired
    private AttemptDAO attemptDAO;

    @Override
    public Attempt get(Long attemptId) {
        return attemptDAO.getRequired(attemptId);
    }

    @Override
    public Attempt get(String ip) {
        return attemptDAO.getTimesByIp(ip);
    }

    @Override
    public Attempt updateTimes(String ip) {
        Attempt attempt = get(ip);
        attempt.setTimes(attempt.getTimes()+1);
        attempt.setLastDate(Calendar.getInstance());

        attemptDAO.update(attempt);
        return attempt;
    }

    @Override
    public void delete(String ip) {
        Attempt attempt = get(ip);
        attemptDAO.delete(attempt);
    }
}
