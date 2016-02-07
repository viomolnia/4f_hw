package io.fourfinanceit.core.rest;

/**
 * Created by Anna on 06.02.2016.
 */

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import io.fourfinanceit.config.HomeworkApplication;
import io.fourfinanceit.rest.loans.LoanResource;
import io.fourfinanceit.rest.users.UserResource;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@WebIntegrationTest(randomPort = true)
@SpringApplicationConfiguration(classes = {HomeworkApplication.class})
public class RESTResourceTest {

    @Value("${local.server.port}")
    private int port;

    protected UserResource userResource;
    protected LoanResource loanResource;

    @Before
    public void init() throws IOException {
        String url = "http://localhost:" + port + "/rest";

        userResource = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new JAXRSContract())
                .target(UserResource.class, url);

        loanResource = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .contract(new JAXRSContract())
                .target(LoanResource.class, url);
    }

}
