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
public class AttemptFactoryImpl implements AttemptFactory{

    @Autowired
    private AttemptDAO attemptDAO;

    @Override
    public Attempt create(String ip) {

        Attempt attempt = new Attempt();
        attempt.setIp(ip);
        attempt.setTimes(0);
        attempt.setLastDate(Calendar.getInstance());

        attemptDAO.create(attempt);
        return attempt;
    }
}
