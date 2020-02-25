package labors.labor14;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author LeeKrane
 */

public class CountryTest {
	@Test
	void serialization_methodsImplemented_correctWriteAndReadObject () throws IOException, ClassNotFoundException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(bos);
		List<Country> expected = Country.readFromCSV("res/labors/labor14/countries.csv");
		os.writeObject(expected);
		for (Country country : expected) {
			country.setName(country.getName().toUpperCase());
			country.setCapital(country.getCapital().toUpperCase());
		}
		ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
		assertEquals(expected, (List<Country>) is.readObject());
	}
	
	void serialization_methodsNotImplemented_standardizedWriteAndReadObject () throws IOException, ClassNotFoundException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(bos);
		List<Country> expected = Country.readFromCSV("res/labors/labor14/countries.csv");
		os.writeObject(expected);
		ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
		assertEquals(expected, (List<Country>) is.readObject());
	}
}