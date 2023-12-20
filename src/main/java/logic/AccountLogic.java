package logic;

import data.AccountRepository;
import model.dto.PersonDto;
import model.internal.Account;
import model.internal.Status;
import model.internal.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;

public class AccountLogic {
// Todo Jira 3 Need refactoring into a rules and controller class

    @Autowired
    AccountRepository accountRepository;

    /**
     * Returns a new account wrap in a Status object if input is valid
     * @param  customer  owner of the new account
     */
    public Status createAccount(PersonDto customer) {
        Status status = AccountValidation.validateCustomer(customer);
        if (status.getStatusCode() == StatusCode.INVALIDINPUT){
            return status;
        }
        status =  postAccount();
        String systemMessage = "Account creation returned status " + status.getStatusCode();
        System.out.println(systemMessage);
        return status;

    }
    /**
     * Returns an account wrap in a Status object if accountNumber is valid
     * @param  accountNumber  id of the account
     */
    public Status getAccount(int accountNumber){
        Status status = getAccountByID(accountNumber);
        status = AccountValidation.validateAccountStatus(status, accountNumber);
        if (status.getStatusCode().equals(StatusCode.INVALIDINPUT)){
            return status;
        }
        String systemMessage = "Account found for id " + accountNumber;
        System.out.println(systemMessage);
        return status;
    }

    /**
     * Returns an account with changed balance wrap in a Status object if accountNumber is valid
     * @param  accountNumber  id of the account
     * @param  amount  amount to withdraw/deposit to account
     */
    public Status depositToAccount(int accountNumber, int amount){
        Status status = getAccount(accountNumber);
        if (status.getStatusCode().equals(StatusCode.INVALIDINPUT)){
            return status;
        }
        Account account = status.getRespondObject().get();
        String systemMessage = "Account found for id " + accountNumber + " deposit to account";
        System.out.println(systemMessage);
        account.modifyBalance(amount);
        return putAccount(account);

    }

    private Status getAccountByID(int accountNumber){
        return accountRepository.getAccount(accountNumber);
    }

    private Status postAccount(){
        return accountRepository.postAccount();
    }

    private Status putAccount(Account account){
        return accountRepository.putAccount(account);
    }
}
