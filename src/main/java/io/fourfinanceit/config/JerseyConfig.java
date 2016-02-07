package io.fourfinanceit.config;

/**
 * Created by Anna on 03.02.2016.
 */

import io.fourfinanceit.rest.loans.LoanResourceImpl;
import io.fourfinanceit.rest.users.UserResourceImpl;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(UserResourceImpl.class);
        register(LoanResourceImpl.class);
    }

}