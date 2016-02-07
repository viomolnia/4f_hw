package io.fourfinanceit.core.database.attempts;

import io.fourfinanceit.core.domain.attempt.Attempt;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
/**
 * Created by Anna on 07.02.2016.
 */
public class AttemptDAOImplTest  extends DatabaseHibernateTest {

    @Test
    @Transactional
    public void testCreateAttempt() {
        Attempt attempt = new Attempt();
        attempt.setIp("localhost");

        assertThat(attempt.getAttemptId(), is(nullValue()));
        attemptDAO.create(attempt);
        assertThat(attempt.getAttemptId(), is(notNullValue()));
        assertSame(attempt.getTimes(), 0);
    }

    @Test
    @Transactional
    public void testGetAttemptByIP() {

        Attempt attempt = new Attempt();
        attempt.setIp("localhost2");

        attemptDAO.create(attempt);

        Attempt attemptFromDB = attemptDAO.getTimesByIp("localhost2");
        assertThat(attemptFromDB, is(notNullValue()));
        assertEquals(attempt.getLastDate(), attemptFromDB.getLastDate());
        assertEquals(attempt.getTimes(), attemptFromDB.getTimes());
    }
}
