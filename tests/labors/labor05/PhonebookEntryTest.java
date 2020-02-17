package labors.labor05;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LeeKrane
 */

public class PhonebookEntryTest {
	@Test
	void compareTo_sortingUnsortedList_correctlySortedList () {
		List<PhonebookEntry> entries = new ArrayList<>(List.of(new PhonebookEntry("+431112", "JoeDoe"), new PhonebookEntry("01112", "JaneDoe"), new PhonebookEntry("01113", "JoeDane"), new PhonebookEntry("+431111", "JaneDane"), new PhonebookEntry("01112", "JoeJane")));
		List<PhonebookEntry> entriesSorted = List.of(new PhonebookEntry("+431111", "JaneDane"), new PhonebookEntry("+431112", "JoeDoe"), new PhonebookEntry("01112", "JaneDoe"), new PhonebookEntry("01112", "JoeJane"), new PhonebookEntry("01113", "JoeDane"));

		Collections.sort(entries);
		assertEquals(entriesSorted, entries);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"+431234567", "+45872365", "+12473488732", "02386578324", "054263874", "000003486"})
	void constructor_validValues_objectCreated (String argument) {
		assertDoesNotThrow(() -> new PhonebookEntry(argument, "JoeDoe"));
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"+0687235", "2343543634"})
	void constructor_invalidValues_throwsIllegalArgumentException (String argument) {
		assertThrows(IllegalArgumentException.class, () -> new PhonebookEntry(argument, "JoeDoe"));
	}
}