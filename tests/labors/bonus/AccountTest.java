package labors.bonus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
	private static Account account;
	
	@BeforeEach
	void createAccount () {
		account = new Account(42, 69, "JoeDoe");
	}
	
	@Test
	void constructor_ownerNull_throwsNullPointerException () {
		assertThrows(NullPointerException.class, () -> new Account(42, 69, null));
	}
	
	@Test
	void constructor_accountNumberLessThan1_throwsIllegalArgumentException () {
		assertThrows(IllegalArgumentException.class, () -> new Account(0, 69, "JoeDoe"));
	}
	
	@Test
	void deposit_amountNegative_throwsIllegalArgumentException () {
		assertThrows(IllegalArgumentException.class, () -> account.deposit(-42));
	}
	
	@Test
	void withdraw_amountNegative_throwsIllegalArgumentException () {
		assertThrows(IllegalArgumentException.class, () -> account.withdraw(-42, 69));
	}
	
	@Test
	void withdraw_feeNegative_throwsIllegalArgumentException () {
		assertThrows(IllegalArgumentException.class, () -> account.withdraw(42, -69));
	}
	
	@Test
	void withdraw_amountAndFeeGreaterThanBalance_throwsIllegalArgumentException () {
		assertThrows(IllegalArgumentException.class, () -> account.withdraw(42, 69));
	}
	
	@Test
	void constructor_validValues_objectCreated () {
		assertDoesNotThrow(() -> new Account(42, 69, "JoeDoe"));
	}
	
	@Test
	void deposit_validValues_raiseBalance () {
		account.deposit(42);
		assertEquals(111, account.getBalance());
	}
	
	@Test
	void withdraw_validValues_lowerBalance () {
		account.withdraw(42, 2);
		assertEquals(25, account.getBalance());
	}
	
	@Test
	void applyInterest_validValues_raiseBalanceWithInterest () {
		account.applyInterest(0.1);
		assertEquals(75, account.getBalance());
	}
}