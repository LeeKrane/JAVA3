package labors.labor05;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author LeeKrane
 */

public class StudentTest {
	@Test
	public void initializationHandlingTest () {
		try {
			new Student("Kolumbus", "Karli", 346543);
			new Student("Marchart", "Manfred", 999999);
			new Student("Bäcker", "Bernhard", 100000);
		} catch (IllegalArgumentException iae) {
			fail();
		}
		try {
			new Student("Grohs", "Gerhard", 69);
			fail();
		} catch (IllegalArgumentException ignored) {
		}
		try {
			new Student("Feuerstein", "Ferdinand", 36042069);
			fail();
		} catch (IllegalArgumentException ignored) {
		}
		try {
			new Student("", "Franziska", 123456);
			fail();
		} catch (IllegalArgumentException ignored) {
		}
		try {
			new Student("Stifter", "", 654321);
			fail();
		} catch (IllegalArgumentException ignored) {
		}
		try {
			new Student("Stifter", null, 654321);
			fail();
		} catch (IllegalArgumentException ignored) {
		}
		try {
			new Student("Davis", "Dennis", 444777);
			new Student("Haberl", "Herbert", 444777);
			fail();
		} catch (IllegalArgumentException ignored) {
		}
	}
}