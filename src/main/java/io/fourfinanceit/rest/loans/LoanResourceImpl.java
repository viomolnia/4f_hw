package io.fourfinanceit.rest.loans;

import io.fourfinanceit.core.commands.loans.CreateLoanCommand;
import io.fourfinanceit.core.commands.loans.CreateLoanResult;
import io.fourfinanceit.core.commands.loans.GetLoanCommand;
import io.fourfinanceit.core.commands.loans.GetLoanResult;
import io.fourfinanceit.core.dto.loan.LoanDTO;
import io.fourfinanceit.core.services.CommandExecutor;
import io.fourfinanceit.core.services.users.UserService;
import io.fourfinanceit.core.services.users.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

import java.math.BigDecimal;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * Created by Anna on 03.02.2016.
 */

@Component
@Path("/loans")
public class LoanResourceImpl {

    private CommandExecutor commandExecutor;

    @Autowired
    public LoanResourceImpl(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @Autowired
    private UserService userService = new UserServiceImpl();

    @Context HttpServletRequest requestContext;

    @GET
    public void activate(@Context HttpServletRequest requestContext){
        String reqIP = requestContext.getRemoteAddr();
        System.out.println("IP: " + reqIP);
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/create")
    public LoanDTO create(LoanDTO loanDTO) {

        activate(requestContext);

        Long id = loanDTO.getUserId();
        BigDecimal amount = loanDTO.getAmount();
        if(id==null){
            id = loanDTO.getUserDTO().getUserId();
        }
        if ((amount.compareTo(new BigDecimal("300")) <=0 ) || userService.get(id).getLoans().size() >=1){

            CreateLoanCommand command = new CreateLoanCommand(
                    loanDTO.getTerm(),
                    loanDTO.getAmount(),
                    id
            );
            CreateLoanResult result = commandExecutor.execute(command);
            return result.getLoan();
        } else {
            throw new NullPointerException("Not allowed!");
        }

    }

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/get/{loanId}")
    public LoanDTO get(@PathParam("loanId") Long loanId) {
        GetLoanCommand command = new GetLoanCommand(loanId);
        GetLoanResult result = commandExecutor.execute(command);
        return result.getLoan();
    }
}
