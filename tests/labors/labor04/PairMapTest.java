package labors.labor04;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author reio
 */
public class PairMapTest
{
	
	public PairMapTest () {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}
	
	/**
	 * Test of put method, of class PairMap.
	 */
	@Test
	public void testPut1() {
		System.out.println("testPut1");
		PairMap<Integer, String> map = new PairMap<>();
		String ret = map.put(1, "Eins");
		assertNull(ret);
		ret = map.put(2, "Zwei");
		assertNull(ret);
		ret = map.put(2, "Noch einmal 2");
		assertEquals("Zwei", ret);
		System.out.println(map);
	}
	
	@Test(expected = NullPointerException.class)
	public void testPut2() {
		System.out.println("testPut2");
		PairMap<Integer, String> map = new PairMap<>();
		String ret = map.put(1, "Eins");
		assertNull(ret);
		ret = map.put(null, "Zwei");
	}
	
	@Test
	public void testPut3() {
		System.out.println("testPut3");
		PairMap<Integer, Double> map = new PairMap<>();
		Double ret = map.put(new Integer(1), 1.1);
		assertNull(ret);
		ret = map.put(new Integer(1), 2.2);
		assertEquals(1.1, ret, 0.0);
		System.out.println(map);
	}
	
	/**
	 * Test of get method, of class PairMap.
	 */
	@Test
	public void testGet1() {
		System.out.println("testGet1");
		PairMap<Integer, String> map = new PairMap<>();
		map.put(1, "Eins");
		map.put(2, "Zwei");
		map.put(2, "Noch einmal 2");
		String ret = map.get(1);
		assertEquals("Eins", ret);
		ret = map.get(2);
		assertEquals("Noch einmal 2", ret);
		System.out.println(map);
	}
	
	@Test
	public void testGet2() {
		System.out.println("testGet2");
		PairMap<Integer, String> map = new PairMap<>();
		map.put(1, "Eins");
		map.put(2, "Zwei");
		String ret = map.get(3);
		assertNull(ret);
		ret = map.get(null);
		assertNull(ret);
		System.out.println(map);
	}
	
	/**
	 * Test of size method, of class PairMap.
	 */
	@Test
	public void testSize1() {
		System.out.println("testSize1");
		PairMap<Integer, String> map = new PairMap<>();
		int s = map.size();
		assertEquals(0, s);
	}
	
	@Test
	public void testSize2() {
		System.out.println("testSize2");
		PairMap<Integer, String> map = new PairMap<>();
		map.put(1, "Eins");
		map.put(2, "Zwei");
		int s = map.size();
		assertEquals(2, s);
	}
	
}
