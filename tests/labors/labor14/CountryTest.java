package labors.labor14;

import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountryTest {
	@Test
	public void serializationTest () {
		List<Country> countries = null;
		try {
			countries = Country.readFromCSV("res/labors/labor14/countries.csv");
		} catch (IOException e) {
			System.err.println(e + ": " + e.getMessage());
		}
		// TODO
	}
}