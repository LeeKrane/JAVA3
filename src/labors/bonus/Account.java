package labors.bonus;

import java.text.NumberFormat;
import java.util.Objects;

/**
 * Account is a bank account with basic services for deposit,
 * withdrawal, and interest. Balance is stored as a long, WITHOUT ANY OVERFLOW PROTECTION.
 */
public class Account {
    private static final NumberFormat fmt = NumberFormat.getCurrencyInstance();

    private long accountNumber;
    private long balance;
    private final String owner;

    /**
     * Sets up the account by defining its owner, account number,
     * and initial balance.
     *
     * @param accountNumber the account number, an identifier for the account
     * @param balance       the initial amount of money in the account.
     * @param owner         name of account holder
     */
    public Account(long accountNumber, long balance, String owner) {
        Objects.requireNonNull(owner);
        if (accountNumber < 1)
            throw new IllegalArgumentException("Accountnumber must be positive.");
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.owner = owner;
    }

    /**
     * Deposit the specified amount into the account.
     *
     * @param amount value to be added to the balance, must not be negative
     */
    public void deposit(long amount) {
        if (amount < 0)
            throw new IllegalArgumentException("Cannot deposit a negative amount.");
        balance += amount;
    }

    public long getBalance() {
        return balance;
    }

    /**
     * Withdraw the specified amount from the account,
     * unless amount is negative, fee is negative, or
     * amount exceeds current balance.
     *
     * @param amount value to be deducted from the balance
     * @param fee    the transaction fee debited from the account
     */
    public void withdraw(long amount, long fee) {
        if (amount < 0)
            throw new IllegalArgumentException("Cannot withdraw a negative amount.");
        if (fee < 0)
            throw new IllegalArgumentException("Fee cannot be negative.");
        if (amount + fee > balance)
            throw new IllegalArgumentException("Requested withdrawal exceeds balance.");
        amount += fee;
        balance -= amount;
    }

    /**
     * Applies interest to the account.
     *
     * @param interestRate the interest applied to the balance
     */
    public void applyInterest(double interestRate) {
        balance += balance * interestRate;
    }

    public String toString() {
        return (accountNumber + "\t" + owner + "\t" + fmt.format(balance));
    }
}