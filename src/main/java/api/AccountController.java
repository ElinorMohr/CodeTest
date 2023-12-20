package api;
import logic.AccountLogic;
import model.dto.PersonDto;
import model.internal.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountController {
    //Todo Jira 1 agree on exact api definition(Only expose whats needed)
    //Todo Jira 4 setup contract test
    @Autowired
    private AccountLogic accountLogic;

    @GetMapping("/account/{id}/balance")
    public Status getAccountBalance(@PathVariable int id) {
        return accountLogic.getAccount(id);
    }

    @GetMapping("/account/{id}/transaction")
    public Status getAccountTransaction(@PathVariable int id) {
        return accountLogic.getAccount(id);
    }

    @PutMapping("/account/{id}")
    public Status postAccount(@PathVariable int id, @RequestBody int amount) {
        return accountLogic.depositToAccount(id, amount);
    }

    @PostMapping("/account")
    public Status postAccount(@RequestBody PersonDto person) {
        return accountLogic.createAccount(person);
    }



}
