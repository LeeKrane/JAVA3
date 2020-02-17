package labors.labor05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LeeKrane
 */

public class StudentTest {
	@ParameterizedTest
	@ValueSource(ints = {346543, 999999, 100000})
	void constructor_validValues_objectCreated (int argument) {
		assertDoesNotThrow(() -> new Student("Joe", "Doe", argument));
	}
	
	@Test
	void constructor_tooLowMatriculationNumber_throwsIllegalArgumentException () {
		assertThrows(IllegalArgumentException.class, () -> new Student("Joe", "Doe", 69));
	}
	
	@Test
	void constructor_tooGreatMatriculationNumber_throwsIllegalArgumentException () {
		assertThrows(IllegalArgumentException.class, () -> new Student("Joe", "Doe", 36042069));
	}
	
	@Test
	void constructor_nameEmptyString_throwsIllegalArgumentException () {
		assertThrows(IllegalArgumentException.class, () -> new Student("", "Doe", 123456));
	}
	
	@Test
	void constructor_firstNameEmptyString_throwsIllegalArgumentException () {
		assertThrows(IllegalArgumentException.class, () -> new Student("Joe", "", 654321));
	}
	
	@Test
	void constructor_nameNull_throwsIllegalArgumentException () {
		assertThrows(IllegalArgumentException.class, () -> new Student(null, "Doe", 654321));
	}
	
	@Test
	void constructor_firstNameNull_throwsIllegalArgumentException () {
		assertThrows(IllegalArgumentException.class, () -> new Student("Joe", null, 654321));
	}
	
	@Test
	void constructor_doubleMatriculationNumber_throwsIllegalArgumentException () {
		assertDoesNotThrow(() -> new Student("Joe", "Doe", 444777));
		assertThrows(IllegalArgumentException.class, () -> new Student("Joe", "Doe", 444777));
	}
}