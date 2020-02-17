package labors.bonus;

import java.util.Collection;

public class AccountHolder {

    private String name;
    private Collection<Account> accounts;

    public AccountHolder(String name, Collection<Account> accounts) {
        this.name = name;
        this.accounts = accounts;
    }

    public long getTotalBalance() {
        return accounts.parallelStream()
                .mapToLong(Account::getBalance)
                .sum();
    }
}
