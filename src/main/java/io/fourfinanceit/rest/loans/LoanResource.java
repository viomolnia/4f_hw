package io.fourfinanceit.rest.loans;

import io.fourfinanceit.core.dto.extension.ExtensionDTO;
import io.fourfinanceit.core.dto.loan.LoanDTO;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.Set;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Anna on 03.02.2016.
 */

public interface LoanResource {


    // rest call to create loan
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/loans/create")
    LoanDTO create(LoanDTO loanDTO);

    // rest call for getting loan by ID
    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/loans/get/{loanId}")
    LoanDTO get(@PathParam("loanId") Long loanId);

    // rest call for getting loan's extensions
    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/loans/{loanId}/get_extensions")
    Set<ExtensionDTO> getLoanExtensions(@PathParam("loanId") Long loanId);

    // rest call for extending a loan
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/loans/{loanId}/extend")
    ExtensionDTO extendLoan(@PathParam("loanId") Long loanId, ExtensionDTO extensionDTO);

    //initializing request for creating loan (getting caller IP
    // and previous attempts to create loan from this IP)
    @GET
    void activate(@Context HttpServletRequest requestContext);

}