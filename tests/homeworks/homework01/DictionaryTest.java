package homeworks.homework01;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * aktueller Test
 *
 * @author maus
 */
public class DictionaryTest {
	
	public DictionaryTest () {
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
		Dictionary<Integer, String> d = new Dictionary<>();
		d.add(new Entry<>(12, "zwoelf"));
		d.add(new Entry<>(12, "zwoelf"));
		d.add(new Entry<>(13, "dreizehhn"));
		d.add(new Entry<>(null, "zwoelf"));
		assertEquals(3, d.getEntries().size());
	}
	
	
	@Test
	public void testConstructor2 () {
		Dictionary<Integer, String> d = new Dictionary<>(false);
		d.add(new Entry<>(12, "zwoelf"));
		d.add(new Entry<>(12, "zwoelf"));
		d.add(new Entry<>(13, "dreizehhn"));
		d.add(new Entry<>(null, "zwoelf"));
		assertEquals(3, d.getEntries().size());
	}
	
	@Test
	public void testConstructor3 () {
		Dictionary<Integer, String> d = new Dictionary<>(true);
		d.add(new Entry<>(12, "zwoelf"));
		d.add(new Entry<>(12, "zwoelf"));
		d.add(new Entry<>(13, "dreizehhn"));
		d.add(new Entry<>(null, "zwoelf"));
		assertEquals(4, d.getEntries().size());
	}
	
	@Test
	public void testConstructorAddAndGetEntries1 () { // nur gleiche Schl�ssel
		Dictionary<Integer, String> d = new Dictionary<>();
		List<Entry<Integer, String>> l = new LinkedList<>();
		l.add(new Entry<>(12, "zwölf"));
		l.add(new Entry<>(44, "vv"));
		
		assertTrue(d.add(12, "zwölf"));
		assertTrue(d.add(new Entry<>(44, "vv")));
		assertFalse(d.add(new Entry<>(12, "zwölf")));
		assertEquals(l, d.getEntries());
	}
	
	@Test
	public void testConstructorAddAndGetEntries2 () { // mehrfache gleiche schl�ssel erlaubt?
		Dictionary<Integer, String> d = new Dictionary<>(true);
		List<Entry<Integer, String>> l = new ArrayList<>();
		l.add(new Entry<>(12, "zwölf"));
		l.add(new Entry<>(44, "vv"));
		l.add(new Entry<>(12, "zwölf"));
		
		assertTrue(d.add(new Entry<>(12, "zwölf")));
		assertTrue(d.add(new Entry<>(44, "vv")));
		assertTrue(d.add(12, "zwölf"));
		assertEquals(l, d.getEntries());
		assertEquals(3, d.getEntries().size());
	}
	
	@Test
	public void testDel1 () { // ersten schl�ssel l�schen
		Dictionary<Integer, String> d = new Dictionary<>(true);
		Entry<Integer, String> e = new Entry<>(12, "zwölf");
		
		assertTrue(d.add(e));
		assertTrue(d.add(new Entry<>(44, "vv")));
		assertTrue(d.add(12, "lol"));
		
		assertEquals(e, d.del(12)); // l�scht 12->zw�lf
		assertNull(d.del(20));
		assertEquals(2, d.getEntries().size());
	}
	
	@Test
	public void testDelAll () {
		Dictionary<Integer, String> d = new Dictionary<>(true);
		List<Entry<Integer, String>> l = new ArrayList<>();
		List<Entry<Integer, String>> deleted = new ArrayList<>();
		l.add(new Entry<>(44, "vv"));
		deleted.add(new Entry<>(12, "zwölf"));
		deleted.add(new Entry<>(12, "lol"));
		
		d.add(new Entry<>(12, "zwölf"));
		d.add(new Entry<>(44, "vv"));
		d.add(12, "lol");
		
		assertEquals(deleted, d.delAll(12)); // löscht 12->zwölf und 12->lol
		assertEquals(l, d.getEntries());
	}
	
	@Test
	public void testIsIn1 () {
		Dictionary<Integer, String> d = new Dictionary<>(true);
		
		assertTrue(d.add(new Entry<>(12, "zwölf")));
		assertTrue(d.add(new Entry<>(44, "vv")));
		
		assertTrue(d.isIn(12));
		assertFalse(d.isIn(13));
		assertFalse(d.isIn(null));
		
		assertTrue(d.add(new Entry<>(null, "null")));
		assertTrue(d.isIn(null));
	}
	
	@Test
	public void testlokupFirst1 () {
		Dictionary<Integer, String> d = new Dictionary<>(true);
		
		assertTrue(d.add(new Entry<>(12, "zwölf")));
		assertTrue(d.add(new Entry<>(44, "vv")));
		assertTrue(d.add(new Entry<>(12, "rofl")));
		
		assertEquals("zwölf", d.lookupFirst(12));
		assertNull(d.lookupFirst(20));
	}
	
	@Test
	public void testlokupFirst2 () {
		Dictionary<Integer, String> d = new Dictionary<>(true);
		
		assertTrue(d.add(new Entry<>(12, "zwölf")));
		assertTrue(d.add(new Entry<>(44, "vv")));
		assertTrue(d.add(new Entry<>(12, "rofl")));
		
		assertEquals("zwölf", d.lookupFirst(12));
		d.del(12);
		assertEquals("rofl", d.lookupFirst(12));
		d.del(12);
		assertNull(d.lookupFirst(12));
	}
	
	@Test
	public void testlokupLast () {
		Dictionary<Integer, String> d = new Dictionary<>(true);
		
		assertTrue(d.add(new Entry<>(12, "zwölf")));
		assertTrue(d.add(new Entry<>(null, "null")));
		assertTrue(d.add(new Entry<>(12, "rofl")));
		
		assertEquals("rofl", d.lookupLast(new Integer(12)));
		assertEquals("null", d.lookupLast(null));
		assertEquals(null, d.lookupLast(42));
	}
	
	@Test
	public void testlokupAll1 () {
		Dictionary<Integer, String> d = new Dictionary<>(true);
		List<Entry<Integer, String>> l = new ArrayList<>();
		
		assertTrue(d.add(new Entry<>(12, "zwölf")));
		assertTrue(d.add(new Entry<>(44, "vv")));
		assertTrue(d.add(new Entry<>(12, "rofl")));
		
		assertEquals(l, d.lookupAll()); // leere liste
	}
	
	@Test
	public void testlokupAll2 () {
		Dictionary<Integer, String> d = new Dictionary<>(true);
		List<Entry<Integer, String>> l = new ArrayList<>();
		
		l.add(new Entry<>(12, "zwölf"));
		l.add(new Entry<>(12, "rofl"));
		
		assertTrue(d.add(new Entry<>(12, "zwölf")));
		assertTrue(d.add(new Entry<>(44, "vv")));
		assertTrue(d.add(new Entry<>(12, "rofl")));
		
		assertEquals(l, d.lookupAll(12));
	}
	
	@Test
	public void testlokupAll3 () {
		Dictionary<Integer, Integer> d = new Dictionary<>(true);
		List<Entry<Integer, Integer>> l = new ArrayList<>();
		
		l.add(new Entry<>(12, 12));
		l.add(new Entry<>(13, 13));
		l.add(new Entry<>(14, 14));
		
		assertTrue(d.add(new Entry<>(14, 14)));
		assertTrue(d.add(new Entry<>(13, 13)));
		assertTrue(d.add(new Entry<>(16, 16)));
		assertTrue(d.add(new Entry<>(new Integer(12), 12)));
		
		assertEquals(l, d.lookupAll(12, 13, 14));
	}
	
	@Test
	public void testToString () {
		Dictionary<Integer, String> d = new Dictionary<>(true);
		
		d.add(new Entry<>(12, "zwölf"));
		d.add(new Entry<>(44, "vv"));
		d.add(new Entry<>(12, "rofl"));
		
		assertEquals("12->zwölf, 44->vv, 12->rofl", d.toString());
	}
	
	
}
