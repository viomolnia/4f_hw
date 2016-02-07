package io.fourfinanceit.rest.loans;

import io.fourfinanceit.core.commands.loans.CreateLoanCommand;
import io.fourfinanceit.core.commands.loans.CreateLoanResult;
import io.fourfinanceit.core.commands.loans.GetLoanCommand;
import io.fourfinanceit.core.commands.loans.GetLoanResult;
import io.fourfinanceit.core.dto.loan.LoanDTO;
import io.fourfinanceit.core.services.CommandExecutor;
import io.fourfinanceit.core.services.services.AttemptFactory;
import io.fourfinanceit.core.services.services.AttemptService;
import io.fourfinanceit.core.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

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
    private AttemptService attemptService;

    @Autowired
    private AttemptFactory attemptFactory;

    @Autowired
    private UserService userService;

    @Context HttpServletRequest requestContext;

    @GET
    public boolean activate(@Context HttpServletRequest requestContext){
//        String reqIP = requestContext.getRemoteAddr();
//        Calendar currentDate = Calendar.getInstance();
//        Calendar lastDate;
//        int times;
//
//        //if there have not been any applies from this IP yet
//        if(attemptService.get(reqIP).equals(null)){
//            attemptFactory.create(reqIP);
//            return true;
//
//        } else {
//            lastDate = attemptService.get(reqIP).getLastDate();
//
//            //if there has been an apply, but not today - delete previous and create new
//            if(lastDate.get(Calendar.DAY_OF_YEAR) != currentDate.get(Calendar.DAY_OF_YEAR)){
//                attemptService.delete(reqIP);
//                attemptFactory.create(reqIP);
//                return true;
//            } else {
//                times = attemptService.get(reqIP).getTimes();
//
//                //if there has been an apply from this IP today, but times were less than 3
//                if(times < 3){
//                    attemptService.updateTimes(reqIP);
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//        }
        return true;
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/create")
    public LoanDTO create(LoanDTO loanDTO) {
        if (activate(requestContext)) {
            Long id = loanDTO.getUserId();
            if(id==null){
                id = loanDTO.getUserDTO().getUserId();
            }

            if(userService.get(id) != null){
                CreateLoanCommand command = new CreateLoanCommand(
                        loanDTO.getTerm(),
                        loanDTO.getAmount(),
                        id
                );

                CreateLoanResult result = commandExecutor.execute(command);
                return result.getLoan();
            } else {
                throw new NullPointerException("Can not apply to loan for unregistered user");
            }

        } else {
            throw new NullPointerException("Increased applies times (more than 3) per day");
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
