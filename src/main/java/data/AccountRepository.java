package data;

import model.internal.Account;
import model.internal.Status;

public interface AccountRepository {
// Todo Jira 5 - Improve stability by integrating to queue service
    public Status postAccount();

    public Status putAccount(Account account);

    public Status getAccount(int accountNumber);

}
