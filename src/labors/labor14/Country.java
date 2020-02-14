package labors.labor14;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Country implements Serializable {
	private String shortMark;
	private String name;
	private String capital;
	private int surface;
	private int population;
	
	Country (String shortMark, String name, String capital, int surface, int population) {
		this.shortMark = shortMark;
		this.name = name;
		this.capital = capital;
		this.surface = surface;
		this.population = population;
	}
	
	static List<Country> readFromCSV (@SuppressWarnings("SameParameterValue") String filename) throws IOException {
		return Files.lines(Paths.get(filename))
				.map(line -> {
					if (line.matches("(\\w+;){3}\\d+;\\d+")) {
						String[] split = line.split(";");
						return new Country(split[0], split[1], split[2], Integer.parseInt(split[3]), Integer.parseInt(split[4]));
					}
					System.err.println("invalid line: " + line);
					return null;
				})
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}
	
	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Country country = (Country) o;
		return surface == country.surface &&
				population == country.population &&
				Objects.equals(shortMark, country.shortMark) &&
				Objects.equals(name, country.name) &&
				Objects.equals(capital, country.capital);
	}
	
	@Override
	public int hashCode () {
		return Objects.hash(shortMark, name, capital, surface, population);
	}
	
	private void readObject (ObjectInputStream is) throws IOException {
		shortMark = is.readUTF();
		name = is.readUTF();
		capital = is.readUTF();
		surface = is.readInt();
		population = is.readInt();
		
	}
	
	private void writeObject (ObjectOutputStream os) throws IOException {
		os.writeUTF(shortMark);
		os.writeUTF(name.toUpperCase());
		os.writeUTF(capital.toUpperCase());
		os.writeInt(surface);
		os.writeInt(population);
	}
	
	public String getName () {
		return name;
	}
	
	public void setName (String name) {
		this.name = name;
	}
	
	public String getCapital () {
		return capital;
	}
	
	public void setCapital (String capital) {
		this.capital = capital;
	}
	
	@Override
	public String toString () {
		return "Country{" +
				"shortMark='" + shortMark + '\'' +
				", name='" + name + '\'' +
				", capital='" + capital + '\'' +
				", surface=" + surface +
				", population=" + population +
				'}';
	}
}
