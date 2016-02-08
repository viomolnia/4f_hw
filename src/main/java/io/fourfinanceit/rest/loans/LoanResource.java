package io.fourfinanceit.rest.loans;

import io.fourfinanceit.core.dto.extension.ExtensionDTO;
import io.fourfinanceit.core.dto.loan.LoanDTO;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.math.BigDecimal;
import java.util.Set;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Anna on 03.02.2016.
 */

public interface LoanResource {

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/loans/create")
    LoanDTO create(LoanDTO loanDTO);

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/loans/get/{loanId}")
    LoanDTO get(@PathParam("loanId") Long loanId);

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/loans/{loanId}/get_extensions")
    Set<ExtensionDTO> getLoanExtensions(@PathParam("loanId") Long loanId);

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/loans/{loanId}/extend")
    ExtensionDTO extendLoan(@PathParam("loanId") Long loanId, ExtensionDTO extensionDTO);

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/loans/{loanId}/get_total_debt")
    BigDecimal getLoanTotalDebt(@PathParam("loanId") Long loanId);

    @GET
    void activate(@Context HttpServletRequest requestContext);

}