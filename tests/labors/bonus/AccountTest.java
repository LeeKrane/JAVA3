package labors.bonus;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
	private static Account account = new Account(42, 69, "Test");
	
	@Test (expected = IllegalArgumentException.class)
	public void constructor_ownerNull_illegalArgumentException () {
		new Account(42, 69, null);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructor_accountNumberLessThan1_illegalArgumentException () {
		new Account(0, 69, "Test");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void deposit_amountNegative_illegalArgumentException () {
		account.deposit(-42);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void withdraw_amountNegative_illegalArgumentException () {
		account.withdraw(-42, 69);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void withdraw_feeNegative_illegalArgumentException () {
		account.withdraw(42, -69);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void withdraw_amountAndFeeGreaterThanBalance_illegalArgumentException () {
		account.withdraw(42, 69);
	}
	
	@Test
	public void constructor_validValues_newAccountObject () {
		new Account(42, 69, "Test");
	}
	
	@Test
	public void deposit_validValues_raiseBalance () {
		account.deposit(42);
		assertEquals(111, account.getBalance());
	}
	
	@Test
	public void withdraw_validValues_lowerBalance () {
		account.withdraw(42, 2);
		assertEquals(67, account.getBalance());
	}
	
	@Test
	public void applyInterest_validValues_raiseBalanceWithInterest () {
		account.applyInterest(0.1);
		assertEquals(73, account.getBalance());
	}
}