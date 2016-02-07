package io.fourfinanceit.rest.users;

/**
 * Created by Anna on 03.02.2016.
 */

import io.fourfinanceit.core.commands.loans.LoanConverter;
import io.fourfinanceit.core.commands.users.CreateUserCommand;
import io.fourfinanceit.core.commands.users.CreateUserResult;
import io.fourfinanceit.core.commands.users.GetUserCommand;
import io.fourfinanceit.core.commands.users.GetUserResult;
import io.fourfinanceit.core.domain.extension.Extension;
import io.fourfinanceit.core.domain.loan.Loan;
import io.fourfinanceit.core.domain.user.User;
import io.fourfinanceit.core.dto.loan.LoanDTO;
import io.fourfinanceit.core.dto.user.UserDTO;
import io.fourfinanceit.core.services.CommandExecutor;
import io.fourfinanceit.core.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Component
@Path("/users")
public class UserResourceImpl {

    private CommandExecutor commandExecutor;

    @Autowired
    public UserResourceImpl(CommandExecutor commandExecutor) {
        this.commandExecutor = commandExecutor;
    }

    @Autowired
    UserService userService;

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/create")
    public UserDTO create(UserDTO userDTO) {
        CreateUserCommand command = new CreateUserCommand(
                userDTO.getName(), userDTO.getSurname(),
                userDTO.getPersonCode()
        );

        try{
            CreateUserResult result = commandExecutor.execute(command);
            return result.getUser();
        }catch(NullPointerException ne){
            throw new NullPointerException("User already exists!");
        }
    }

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/get/{userId}")
    public UserDTO get(@PathParam("userId") Long userId) {
        try{
            GetUserCommand command = new GetUserCommand(userId);
            GetUserResult result = commandExecutor.execute(command);
            return result.getUser();
        }catch(IllegalArgumentException ie){
            throw new IllegalArgumentException("User doesn't exist!");
        }
    }

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    @Path("/{userId}/get_loans")
    public Set<LoanDTO> getUserLoans(@PathParam("userId") Long userId) {
        Set<LoanDTO> resultSetDTO = new HashSet<LoanDTO>();
        Set<Loan> resultSet = new HashSet<Loan>();
        User user = userService.get(userId);

        if(user.getLoans() != null){
            resultSet = user.getLoans();
        } else{
            return resultSetDTO;
        }

        for(Loan i:resultSet){
            resultSetDTO.add(new LoanConverter().convert(i));
        }
        return resultSetDTO;
    }

    @GET
    @Path("/{userId}/get_total_debt")
    public BigDecimal getTotalDebt(@PathParam("userId") Long userId) {

        Loan lastLoan = (Loan) getLastElement(userService.get(userId).getLoans());
        BigDecimal totalDebt = lastLoan.getIndexedAmount();

        //if loans has no extensions
        if(lastLoan.getExtensions() == null || lastLoan.getExtensions().size() == 0){
            return totalDebt;
        } else {
            Set<Extension> lastLoanExtensions = lastLoan.getExtensions();
            totalDebt = totalDebt.add(extensionsTotalCost(lastLoanExtensions));
            return totalDebt;
        }
    }

    public static Object getLastElement(final Collection c) {
        final Iterator itr = c.iterator();
        Object lastElement = itr.next();
        while(itr.hasNext()) {
            lastElement=itr.next();
        }
        return lastElement;
    }

    public static BigDecimal extensionsTotalCost(final Collection c) {
        final Iterator itr = c.iterator();
        BigDecimal totalCost = new BigDecimal(0);
        Extension lastElement = (Extension) itr.next();
        while(itr.hasNext()) {
            lastElement= (Extension) itr.next();
            totalCost = totalCost.add(lastElement.getCost());
        }
        return totalCost;
    }

}
