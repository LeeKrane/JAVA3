package labors.labor14;

import org.junit.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountryTest {
	@Test
	public void serializationTest () {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
			 ObjectOutputStream os = new ObjectOutputStream(bos)) {
			List<Country> expected = Country.readFromCSV("res/labors/labor14/countries.csv");
			os.writeObject(expected);
			capitalize(expected); // capitalization of expected list
			try (ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()))) {
				List<Country> actual = (List<Country>) is.readObject();
				assertEquals(expected, actual);
				System.out.println(actual);
			}
		} catch (IOException | ClassNotFoundException e) {
			System.err.println(e + ": " + e.getMessage());
		}
	}
	
	private void capitalize (List<Country> countries) {
		for (Country country : countries) {
			country.setName(country.getName().toUpperCase());
			country.setCapital(country.getCapital().toUpperCase());
		}
	}
}