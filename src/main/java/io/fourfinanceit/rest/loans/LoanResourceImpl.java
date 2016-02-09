package io.fourfinanceit.rest.loans;

import io.fourfinanceit.core.commands.extensions.CreateExtensionCommand;
import io.fourfinanceit.core.commands.extensions.CreateExtensionResult;
import io.fourfinanceit.core.commands.extensions.ExtensionConverter;
import io.fourfinanceit.core.commands.loans.CreateLoanCommand;
import io.fourfinanceit.core.commands.loans.CreateLoanResult;
import io.fourfinanceit.core.commands.loans.GetLoanCommand;
import io.fourfinanceit.core.commands.loans.GetLoanResult;
import io.fourfinanceit.core.domain.extension.Extension;
import io.fourfinanceit.core.domain.loan.Loan;
import io.fourfinanceit.core.dto.extension.ExtensionDTO;
import io.fourfinanceit.core.dto.loan.LoanDTO;
import io.fourfinanceit.core.services.CommandExecutor;
import io.fourfinanceit.core.services.attempts.AttemptFactory;
import io.fourfinanceit.core.services.attempts.AttemptService;
import io.fourfinanceit.core.services.loans.LoanService;
import io.fourfinanceit.core.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

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

    @Autowired
    private LoanService loanService;

    @Context HttpServletRequest requestContext;

    private static String reqIP;

    @GET
    public boolean activate(@Context HttpServletRequest requestContext){
        reqIP = requestContext.getRemoteAddr();
        Calendar currentDate = Calendar.getInstance();
        Calendar lastDate;
        int times;

        //if there have not been any applies from this IP yet
        if(attemptService.get(reqIP) == null){
            attemptFactory.create(reqIP);
            return true;

        } else {
            lastDate = attemptService.get(reqIP).getLastDate();

            //if there has been an apply, but not today - delete previous and create new
            if(lastDate.get(Calendar.DAY_OF_YEAR) != currentDate.get(Calendar.DAY_OF_YEAR)){
                attemptService.delete(reqIP);
                attemptFactory.create(reqIP);
                return true;
            } else {
                times = attemptService.get(reqIP).getTimes();

                //if there has been an apply from this IP today, but times were less than 3
                if(times < 100){
                    return true;
                } else {
                    return false;
                }
            }
        }
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
                attemptService.updateTimes(reqIP);
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

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/{loanId}/get_extensions")
    public Set<ExtensionDTO> getLoanExtensions(@PathParam("loanId") Long loanId) {
        Set<ExtensionDTO> resultSetDTO = new HashSet<ExtensionDTO>();
        Set<Extension> resultSet = new HashSet<Extension>();
        Loan loan = loanService.get(loanId);

        if(loan.getExtensions() != null){
            resultSet = loan.getExtensions();
        } else{
            return resultSetDTO;
        }

        for(Extension i:resultSet){
            resultSetDTO.add(new ExtensionConverter().convert(i));
        }
        return resultSetDTO;
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/{loanId}/extend")
    public ExtensionDTO extendLoan(@PathParam("loanId") Long loanId, ExtensionDTO extensionDTO) {
        Long id = extensionDTO.getLoanId();
        if(id==null){
           id = extensionDTO.getLoanDTO().getLoanId();
        }

        if(loanService.get(id) != null){
            CreateExtensionCommand command = new CreateExtensionCommand(
                extensionDTO.getWeeksCount(),
                id
            );

            CreateExtensionResult result = commandExecutor.execute(command);
                return result.getExtension();
        } else {
            throw new NullPointerException("Can not add extension to loan, that doesn't exist");
        }
    }
}
