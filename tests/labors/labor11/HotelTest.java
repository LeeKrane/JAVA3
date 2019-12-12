package labors.labor11;

import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;

public class HotelTest {
    private static String resourcePath = "resources/labors/labor11/";

    private static Map<String, Short> getPropertiesMap() {
        Map<String, Short> ret = new LinkedHashMap<>();
        ret.put("name", (short) 64);
        ret.put("location", (short) 64);
        ret.put("size", (short) 4);
        ret.put("smoking", (short) 1);
        ret.put("rate", (short) 8);
        ret.put("date", (short) 10);
        ret.put("owner", (short) 8);
        return ret;
    }

    @Test
    public void readProperties_validFile_properties() throws IOException {
        Map<String, Short> expected = getPropertiesMap();
        Map<String, Short> result = Hotel.readProperties(resourcePath + "hotels.db");
        assertNotNull(result);
        assertEquals(expected.size(), result.size());
        assertEquals(expected, result);
        //AbstractMap.equals prüft nicht auf Reihenfolge der Keys
        assertEquals(new ArrayList<>(expected.entrySet()), new ArrayList<>(result.entrySet()));
    }

    @Test
    public void getStartingOffset_validFile_offsetOfFirstHotel() throws IOException {
        int offset = Hotel.getStartingOffset(resourcePath + "hotels.db");
        assertEquals(74, offset);
    }

    @Test
    public void readHotels_invalidFile_exception() throws IOException {
        String filename = resourcePath + "invalid.db";
        try {
            Hotel.readHotels(filename);
            fail();
        } catch (IllegalArgumentException e) {
            assertTrue(e.getMessage().contains(filename));
        }
    }
/*
    @Test
    public void readHotels_validFile_allHotels() throws IOException {
        Set<Hotel> result = Hotel.readHotels("hotels.db");
        assertNotNull(result);
        assertEquals(31, result.size());
        Hotel deleted = new Hotel("Hotel Nr. Eins", "Wien", 4, false, 40_000, LocalDate.of(2018, 12, 10), "Michael");
        Hotel contained = new Hotel("Mausefalle", "Krems", 36, true, 10_000, LocalDate.of(2019, 11, 12), "MAUS");
        assertTrue(result.contains(contained));
        assertFalse(result.contains(deleted));
        Hotel first = new Hotel("Bed & Breakfast & Business", "Atlantis", 4, false, 19_000, LocalDate.of(2003, 10, 05), "");
        SortedSet<Hotel> sorted = (SortedSet<Hotel>) result;
        assertEquals(first, sorted.first());
        Hotel last = new Hotel("Dew Drop Inn", "Xanadu", 4, true, 20_000, LocalDate.of(2003, 01, 19), "");
        assertEquals(last, sorted.last());
    }

    @Test
    public void hotelConstructor_state_result() {
        byte[] data = null;
        Map<String, Short> properties = null;
        Hotel hotel = new Hotel(data, properties);
        throw new UnsupportedOperationException("TODO");
    }

    @Test
    public void getBytes_state_result() {
        Hotel hotel = null;
        byte[] bytes = hotel.getBytes();
        throw new UnsupportedOperationException("TODO");
    }
 */
}