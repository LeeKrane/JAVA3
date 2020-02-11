package labors.labor14;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Country implements Serializable {
	private String shortMark;
	private String name;
	private String capital;
	private int surface;
	private int population;
	
	Country () {
	
	}
	
	Country (String shortMark, String name, String capital, int surface, int population) {
		this.shortMark = shortMark;
		this.name = name;
		this.capital = capital;
		this.surface = surface;
		this.population = population;
	}
	
	static List<Country> readFromCSV (String filename) throws IOException {
		return Files.lines(Paths.get(filename))
				.map(line -> {
					if (line.matches("(\\w+;){3}\\d+;\\d+")) {
						String[] split = line.split(";");
						return new Country(split[0], split[1], split[2], Integer.parseInt(split[3]), Integer.parseInt(split[4]));
					}
					System.err.println("invalid line: " + line);
					return null;
				})
				.collect(Collectors.toList());
	}
	
	// TODO: serialization management
}
