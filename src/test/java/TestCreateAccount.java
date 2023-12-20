
import data.AccountRepository;
import logic.AccountLogic;
import model.dto.PersonDto;
import model.internal.Status;
import model.internal.StatusCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

// User story - As an end-user I want to create an account so that i can can use banking services.
public class TestCreateAccount {
    @Mock
    AccountRepository mockRepository;

    @InjectMocks
    AccountLogic logic;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void requestAccountWithValidInfo(){
        //Setup
        PersonDto person = new PersonDto("Inger", "DK");
        when(mockRepository.postAccount()).thenReturn(new Status(StatusCode.COMPLETED, "OK"));

        //Test
        Status status = logic.createAccount(person);
        Assertions.assertEquals(StatusCode.COMPLETED, status.getStatusCode());

    }

    @Test
    public void requestAccountWithIllegalNationality(){
        //Only account support for nationality DK
        Status status = logic.createAccount(new PersonDto("Inger", "US"));
        Assertions.assertEquals(StatusCode.INVALIDINPUT, status.getStatusCode());

    }

    @Test
    public void requestAccountNullValues(){
        //Test null nationality
        Status statusNullNationality = logic.createAccount(new PersonDto("Inger", null));
        Assertions.assertEquals(StatusCode.INVALIDINPUT, statusNullNationality.getStatusCode());


        //Test null name
        Status statusNullName = logic.createAccount(new PersonDto(null, "DK"));
        Assertions.assertEquals(StatusCode.INVALIDINPUT, statusNullName.getStatusCode());


        //Test null dto
        Status statusNullPerson = logic.createAccount(null);
        Assertions.assertEquals(StatusCode.INVALIDINPUT, statusNullPerson.getStatusCode());
    }

}
