package logic;

import model.dto.PersonDto;
import model.internal.Status;
import model.internal.StatusCode;

public class AccountValidation {
    /**
     * Returns a Status object with status of validation
     * @param  customer  customer to run validation on
     */
    static Status validateCustomer(PersonDto customer){
        if (customer == null || customer.getName() == null || customer.getNationality() == null){
            String systemMessage = "Missing input";
            System.out.println(systemMessage);
            return new Status(StatusCode.INVALIDINPUT, systemMessage);
        }
        if (!customer.getNationality().equals("DK")){
            String systemMessage = "Only account support for nationality DK";
            System.out.println(systemMessage);
            return new Status(StatusCode.INVALIDINPUT, systemMessage);
        }
        return new Status(StatusCode.COMPLETED, "OK");
    }

    /**
     * Returns a Status object with status of search for account
     * @param  status  Status object received by communication with external system
     * @param  accountNumber  Account searched for
     */
    static Status validateAccountStatus(Status status, int accountNumber){
        if (status.getRespondObject().isEmpty()){
            String systemMessage = "No account found for id " + accountNumber;
            System.out.println(systemMessage);
            return new Status(StatusCode.INVALIDINPUT, systemMessage);
        }
        return status;
    }


}
