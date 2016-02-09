package io.fourfinanceit.rest.users;

/**
 * Created by Anna on 03.02.2016.
 */

import io.fourfinanceit.core.dto.loan.LoanDTO;
import io.fourfinanceit.core.dto.user.UserDTO;

import javax.ws.rs.*;
import java.util.Set;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public interface UserResource {

    // rest call for creating user
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/users/create")
    UserDTO create(UserDTO userDTO);

    // rest call for getting user by id
    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/users/get/{userId}")
    UserDTO get(@PathParam("userId") Long userId);

    // rest call for getting user's loans
    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/users/{userId}/get_loans")
    Set<LoanDTO> getUserLoans(@PathParam("userId") Long userId);

}