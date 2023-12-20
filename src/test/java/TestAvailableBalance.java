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

// User story -  As an end-user I want to get the balance of my account so that I can see it.
public class TestAvailableBalance {

    @Mock
    AccountRepository mockRepository;

    @InjectMocks
    AccountLogic logic;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void requestAccountWithValidID(){
        //Setup
        int id = 1001;
        when(mockRepository.getAccount(id)).thenReturn(new Status(StatusCode.COMPLETED, "OK", Optional.of(new Account(1000, id))));

        //Test
        Status status = logic.getAccount(id);
        Assertions.assertEquals(StatusCode.COMPLETED, status.getStatusCode());

    }

    @Test
    public void requestAccountWithInvalidID(){
        //Setup
        int id = 1002;
        when(mockRepository.getAccount(id)).thenReturn(new Status(StatusCode.ERROR, "ERROR - no account found"));

        //Test no account found
        Status status = logic.getAccount(id);
        Assertions.assertEquals(StatusCode.INVALIDINPUT, status.getStatusCode());

    }
}
