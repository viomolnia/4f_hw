package io.fourfinanceit.core.database.attempts;

/**
 * Created by Anna on 06.02.2016.
 */

import io.fourfinanceit.config.HomeworkApplication;
import io.fourfinanceit.core.database.AttemptDAO;
import io.fourfinanceit.core.database.LoanDAO;
import io.fourfinanceit.core.database.UserDAO;
import org.hibernate.SessionFactory;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {HomeworkApplication.class})

public abstract class DatabaseHibernateTest {

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    protected UserDAO userDAO;

    @Autowired
    protected LoanDAO loanDAO;

    @Autowired
    protected AttemptDAO attemptDAO;


}