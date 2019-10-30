package labors.labor05;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static org.junit.Assert.*;

/**
 * @author LeeKrane
 */

public class PhonebookEntryTest
{
	@Test
	public void sortingTest ()
	{
		List<PhonebookEntry> entries = new ArrayList<>();
		ArrayList<PhonebookEntry> entriesSorted = new ArrayList<>();
		
		entries.add(new PhonebookEntry("+431112", "Tobi"));
		entries.add(new PhonebookEntry("01112", "Paul"));
		entries.add(new PhonebookEntry("01113", "Karli"));
		entries.add(new PhonebookEntry("+431111", "Krane"));
		entries.add(new PhonebookEntry("01112", "Steffan"));
		
		entriesSorted.add(new PhonebookEntry("+431111", "Krane"));
		entriesSorted.add(new PhonebookEntry("+431112", "Tobi"));
		entriesSorted.add(new PhonebookEntry("01112", "Paul"));
		entriesSorted.add(new PhonebookEntry("01112", "Steffan"));
		entriesSorted.add(new PhonebookEntry("01113", "Karli"));
		
		List<PhonebookEntry> entriesSortedReverse = (ArrayList<PhonebookEntry>) entriesSorted.clone();
		entriesSortedReverse.sort(Collections.reverseOrder());
		
		Collections.sort(entries);
		assertEquals(entriesSorted, entries);
		System.out.println(entries);
		
		entries.sort(Collections.reverseOrder());
		assertEquals(entriesSortedReverse, entries);
		System.out.println(entries);
		
		entries.add(new PhonebookEntry("01115", "Steffan"));
		entries.add(new PhonebookEntry("01113", "Steffan"));
		entries.sort(Comparator.comparing(PhonebookEntry::getName).thenComparing(Comparator.comparing(PhonebookEntry::getNumber).reversed()));
		System.out.println(entries);
	}
	
	@Test
	public void initializationHandlingTest ()
	{
		try
		{
			new PhonebookEntry("+431234567", "correct");
			new PhonebookEntry("+45872365", "correct");
			new PhonebookEntry("+12473488732", "correct");
			new PhonebookEntry("02386578324", "correct");
			new PhonebookEntry("054263874", "correct");
			new PhonebookEntry("000003486", "correct");
		}
		catch (IllegalArgumentException iae)
		{
			fail();
		}
		try
		{
			new PhonebookEntry("+0687235", "incorrect");
			fail();
		}
		catch (IllegalArgumentException ignored)
		{ }
		try
		{
			new PhonebookEntry("2343543634", "incorrect");
		}
		catch (IllegalArgumentException ignored)
		{ }
	}
}