package io.fourfinanceit.core.database.attempts;

import io.fourfinanceit.core.database.AttemptDAO;
import io.fourfinanceit.core.domain.attempt.Attempt;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

/**
 * Created by Anna on 07.02.2016.
 */
@Component
public class AttemptDAOImpl extends CRUDOperationDAOImpl<Attempt, Long> implements AttemptDAO {
    @Override
    public Attempt getTimesByIp(String ip) {
        return (Attempt) getCurrentSession()
                .createCriteria(Attempt.class)
                .add(Restrictions
                        .eq("ip", ip))
                .uniqueResult();
    }
}
