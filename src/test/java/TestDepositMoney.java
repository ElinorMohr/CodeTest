import data.AccountRepository;
import logic.AccountLogic;
import model.internal.Account;
import model.internal.Status;
import model.internal.StatusCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.when;

// User story -  As an end-user I want to change balance of my account so that deposit/withdraw money.
public class TestDepositMoney {

    @Mock
    AccountRepository mockRepository;

    @InjectMocks
    AccountLogic logic;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void requestDeposit(){
        //Setup
        int id = 1001;
        int amount = 100;
        Account account = new Account(1200, id);
        when(mockRepository.getAccount(id)).thenReturn(new Status(StatusCode.COMPLETED, "OK", Optional.of(account)));
        when(mockRepository.putAccount(account)).thenReturn(new Status(StatusCode.COMPLETED, "OK"));

        //Test deposit
        Status statusDepositToAccount = logic.depositToAccount(id, amount);
        Assertions.assertEquals(StatusCode.COMPLETED, statusDepositToAccount.getStatusCode());
    }

    @Test
    public void requestWithdraw() {
        //Setup
        int id = 1002;
        int amount = -100;
        Account account = new Account(1200, id);
        when(mockRepository.getAccount(id)).thenReturn(new Status(StatusCode.COMPLETED, "OK", Optional.of(account)));
        when(mockRepository.putAccount(account)).thenReturn(new Status(StatusCode.COMPLETED, "OK"));

        //Test withdraw
        Status statusDepositToAccount = logic.depositToAccount(id, amount);
        Assertions.assertEquals(StatusCode.COMPLETED, statusDepositToAccount.getStatusCode());
    }

    @Test
    public void requestToInvalidAccount(){
        //Setup
        int id = 1002;
        when(mockRepository.getAccount(id)).thenReturn(new Status(StatusCode.ERROR, "ERROR - no account found"));

        //Test deposit to invalid account
        Status statusInvalidAccount = logic.depositToAccount(id, 100);
        Assertions.assertEquals(StatusCode.INVALIDINPUT,statusInvalidAccount.getStatusCode());

    }

    @Test
    public void depositRules(){
        //Todo Jira 2 clarify rules for overdraft
        Account account = new Account(1200, 1);
        account.modifyBalance(100);
        Assertions.assertEquals(1300 , account.getBalance());
        account.modifyBalance(-1400);
        Assertions.assertEquals(-100 , account.getBalance());
    }


}
