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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;

// User story -  As an end-user I want to get a list of transaction so that I can keep track.
public class TestListTransactions {

    @Mock
    AccountRepository mockRepository;

    @InjectMocks
    AccountLogic logic;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void requestAccountWithTransaction(){
        //Setup
        ArrayList<Integer> transactions = new ArrayList<>(Arrays. asList(100, -500, -735, 400));
        int id = 1002;
        Account account = new Account(1000, id , transactions);
        when(mockRepository.getAccount(id)).thenReturn(new Status(StatusCode.COMPLETED, "OK", Optional.of(account)));

        //Test account with transaction
        Status statusAccountWithTransaction = logic.getAccount(id);
        Assertions.assertEquals(StatusCode.COMPLETED,statusAccountWithTransaction.getStatusCode());
        Assertions.assertEquals(statusAccountWithTransaction.getRespondObject().get().getTransactions().size(),4);

    }


    @Test
    public void requestDepositTestTransaction(){
        //Setup
        ArrayList<Integer> transactions = new ArrayList<>(Arrays. asList(100, -500, -735, 400));
        int id = 1002;
        Account account = new Account(1000, id , transactions);
        when(mockRepository.getAccount(id)).thenReturn(new Status(StatusCode.COMPLETED, "OK", Optional.of(account)));
        when(mockRepository.putAccount(account)).thenReturn(new Status(StatusCode.COMPLETED, "OK"));

        //Test create new transaction(from 4 to 5)
        logic.depositToAccount(id, 100);
        Status statusAccountWithTransaction = logic.getAccount(id);
        Assertions.assertEquals(StatusCode.COMPLETED,statusAccountWithTransaction.getStatusCode());
        Assertions.assertEquals(statusAccountWithTransaction.getRespondObject().get().getTransactions().size(),5);
    }
}
