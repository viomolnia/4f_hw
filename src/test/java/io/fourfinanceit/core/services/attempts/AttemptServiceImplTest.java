package io.fourfinanceit.core.services.attempts;

import io.fourfinanceit.core.database.AttemptDAO;
import io.fourfinanceit.core.domain.attempt.Attempt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;

/**
 * Created by Anna on 07.02.2016.
 */
@RunWith(MockitoJUnitRunner.class)

public class AttemptServiceImplTest {
    @Mock
    private AttemptDAO attemptDAO;

    @InjectMocks
    private AttemptFactory attemptFactory = new AttemptFactoryImpl();
    @InjectMocks    private AttemptService attemptService = new AttemptServiceImpl();

    @Test
    public void getByIDShouldReturnAttempt() {

        Attempt gotAttempt = attemptService.get(1L);
        InOrder inOrder = inOrder(attemptDAO);
        inOrder.verify(attemptDAO).getRequired(1L);
    }

    @Test
    public void getByIPShouldReturnAttempt() {

        Attempt gotAttempt = attemptService.get("aaa55");
        InOrder inOrder = inOrder(attemptDAO);
        inOrder.verify(attemptDAO).getTimesByIp("aaa55");
    }

    @Test
    public void updateAttemptsShouldIncreaseTimes() {
        Attempt attempt = attemptFactory.create("sss55");
        doReturn(attempt)
                .when(attemptDAO).getTimesByIp("sss55");

        Attempt gotAttempt = attemptService.updateTimes("sss55");
        InOrder inOrder = inOrder(attemptDAO);
        inOrder.verify(attemptDAO).update(gotAttempt);
    }
}
