package homeworks.homework01;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * aktueller Extratest
 *
 * @author maus
 */
public class EntryTest {
	
	public EntryTest () {
	}
	
	@BeforeClass
	public static void setUpClass () throws Exception {
	}
	
	@AfterClass
	public static void tearDownClass () throws Exception {
	}
	
	@Before
	public void setUp () {
	}
	
	@After
	public void tearDown () {
	}
	
	
	@Test
	public void testConstructor1 () {
		Entry<Integer, String> e = new Entry<>(); // eintrag mit null/null als schlüsselwertepaar
		
		assertNull(e.getKey());
		assertNull(e.getValue());
	}
	
	@Test
	public void testConstructor2 () {
		Entry<Integer, String> e = new Entry<>(12, "lol");
		
		assertEquals(new Integer(12), e.getKey());
		assertEquals("lol", e.getValue());
	}
	
	@Test
	public void testConstructor3 () {
		Entry<Integer, String> e = new Entry<>(null, "test");
		assertNull(e.getKey());
		assertEquals("test", e.getValue());
	}
	
	@Test
	public void testToString1 () {
		Entry<Integer, String> e = new Entry<>(12, "lol");
		String s1 = e.toString();
		String s2 = "12->lol";
		assertEquals(s2, s1);
	}
	
	@Test
	public void testHashCode1 () {
		Entry<Integer, String> e1 = new Entry<>(12, "lol");
		Entry<Integer, String> e2 = new Entry<>(12, "lol");
		assertEquals(e1.hashCode(), e2.hashCode());
	}
	
	
	@Test
	public void testEquals1 () {
		Entry<Integer, String> e1 = new Entry<>(12, "lol");
		Entry<Integer, String> e2 = new Entry<>(12, "lol");
		assertEquals(e1, e2);
	}
	
	@Test
	public void testequals2 () {
		Entry<Integer, String> e1 = new Entry<>(12, "lol");
		Entry<Integer, String> e2 = new Entry<>(13, "lol");
		assertFalse(e1.equals(e2));
	}
	
	@Test
	public void testequals3 () {
		Entry<Integer, String> e1 = new Entry<>(12, "lol");
		Entry<Integer, String> e2 = new Entry<>(12, "rofl");
		assertFalse(e1.equals(e2));
	}
	
	@Test
	public void testequals4 () {
		Entry<Integer, String> e1 = new Entry<>();
		Entry<Integer, String> e2 = new Entry<>();
		assertTrue(e1.equals(e2));
	}
	
	@Test
	public void testequals5 () {
		Entry<Integer, String> e1 = new Entry<>(12, null);
		Entry<Integer, String> e2 = new Entry<>(12, "null");
		assertFalse(e1.equals(e2));
	}
	
	@Test
	public void testCompareTo1 () {
		Entry<Integer, String> e1 = new Entry<>(12, "lol");
		Entry<Integer, String> e2 = new Entry<>(12, "rofl");
		assertEquals(e1.compareTo(e2), 0);
	}
	
	@Test
	public void testCompareTo2 () {
		Entry<Integer, String> e1 = new Entry<>(12, "lol");
		Entry<Integer, String> e2 = new Entry<>(13, "rofl");
		assertTrue(e1.compareTo(e2) < 0);
	}
	
	@Test
	public void testCompareTo3 () {
		Entry<Integer, String> e1 = new Entry<>(13, "lol");
		Entry<Integer, String> e2 = new Entry<>(12, "rofl");
		assertTrue(e1.compareTo(e2) > 0);
	}
	
	@Test(expected = NullPointerException.class)
	public void testCompareTo4 () {
		Entry<Integer, String> e1 = new Entry<>(null, "lol");
		Entry<Integer, String> e2 = new Entry<>(null, "rofl");
		assertTrue(e1.compareTo(e2) == 0);
	}
	
	@Test(expected = NullPointerException.class)
	public void testCompareTo5 () {
		Entry<Integer, String> e1 = new Entry<>(10, "lol");
		Entry<Integer, String> e2 = new Entry<>(null, "rofl");
		e1.compareTo(e2);
	}
}
