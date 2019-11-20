package labors.labor09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author LeeKrane
 */

public class Schueler implements Comparable<Schueler> {
	private String klasse;
	private String name;
	private String vorname;
	private char geschlecht;
	private LocalDate geboren;
	private String religion;
	
	Schueler (String input) {
		String[] split = input.split(";");
		klasse = split[0];
		name = split[1];
		vorname = split[2];
		geschlecht = split[3].charAt(0);
		geboren = LocalDate.parse(split[4], DateTimeFormatter.ofPattern("dd.MM.uuuu").withResolverStyle(ResolverStyle.STRICT)); // y: year of era, u: year. ResolverStyle.STRICT needs year (u)
		religion = split[5];
	}
	
	static Schueler makeSchueler (String input) {
		return new Schueler(input);
	}
	
	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Schueler schueler = (Schueler) o;
		return Objects.equals(klasse, schueler.klasse) &&
				Objects.equals(name, schueler.name) &&
				Objects.equals(vorname, schueler.vorname) &&
				Objects.equals(geboren, schueler.geboren);
	}
	
	@Override
	public int hashCode () {
		return Objects.hash(klasse, name, vorname, geboren);
	}
	
	String getKlasse () {
		return klasse;
	}
	
	String getName () {
		return name;
	}
	
	String getVorname () {
		return vorname;
	}
	
	char getGeschlecht () {
		return geschlecht;
	}
	
	int getAge (LocalDate date) {
		int difference = date.getYear() - geboren.getYear();
		if (geboren.getMonthValue() > date.getMonthValue() || (geboren.getMonthValue() == date.getMonthValue() && geboren.getDayOfMonth() > date.getDayOfMonth()))
			difference--;
		if (difference < 0)
			throw new IllegalArgumentException("Student has not yet been born yet on: " + date.toString());
		return difference;
	}
	
	LocalDate getGeboren () {
		return geboren;
	}
	
	String getReligion () {
		return religion;
	}
	
	@Override
	public int compareTo (Schueler other) {
		return Comparator.comparing(Schueler::getKlasse).thenComparing(Schueler::getName).thenComparing(Schueler::getVorname).thenComparing(Schueler::getGeboren).compare(this, other);
	}
}

class SchuelerVerwaltung {
	private Collection<Schueler> schuelerCollection;
	
	SchuelerVerwaltung (String filePath) throws IOException {
		schuelerCollection = Files.lines(Paths.get(filePath))
				.skip(1)
				.map(Schueler::new)
				.collect(Collectors.toCollection(TreeSet::new));
	}
	
	Set<Schueler> getSchuelerFromKlasse (String klasse) {
		return schuelerCollection.stream()
				.filter(schueler -> schueler.getKlasse().equals(klasse))
				.collect(Collectors.toCollection(TreeSet::new));
	}
	
	Set<Schueler> containsName (String name, boolean komplett) {
		return schuelerCollection.stream()
				.filter(schueler -> komplett ? schueler.getName().equals(name) : schueler.getName().contains(name))
				.collect(Collectors.toCollection(TreeSet::new));
	}
	
	Set<Schueler> getAllWith (char geschlecht) {
		return schuelerCollection.stream()
				.filter(schueler -> schueler.getGeschlecht() == geschlecht)
				.collect(Collectors.toCollection(TreeSet::new));
	}
	
	Set<Schueler> getGeborenBis (LocalDate datum, boolean vorNach) {
		Stream<Schueler> schuelerStream = schuelerCollection.stream();
		if (vorNach)
			schuelerStream = schuelerStream.filter(schueler -> schueler.getGeboren().compareTo(datum) <= 0);
		else
			schuelerStream = schuelerStream.filter(schueler -> schueler.getGeboren().compareTo(datum) > 0);
		return schuelerStream.collect(Collectors.toCollection(TreeSet::new));
	}
	
	Map<String, Integer> getKlassenAnzahl () {
		Map<String, Integer> klassenAnzahl = new TreeMap<>();
		for (Schueler schueler : schuelerCollection) {
			if (!klassenAnzahl.containsKey(schueler.getKlasse()))
				klassenAnzahl.put(schueler.getKlasse(), (int) schuelerCollection.stream()
						.filter(schueler1 -> schueler1.getKlasse().equals(schueler.getKlasse()))
						.count());
		}
		return klassenAnzahl;
	}
	
	Map<String, Map<String, List<String>>> getReligionsZugehoerigkeit () {
		Map<String, Map<String, List<String>>> religionsZugehoerigkeit = new HashMap<>();
		
		for (Schueler schueler : schuelerCollection) {
			if (!religionsZugehoerigkeit.containsKey(schueler.getReligion())) {
				Map<String, List<String>> schuelerMap = new HashMap<>();
				
				for (Schueler schueler1 : schuelerCollection) {
					if (schueler.getReligion().equals(schueler1.getReligion()))
						schuelerMap.put(schueler1.getKlasse(), schuelerCollection.stream()
								.filter(schueler2 -> schueler2.getKlasse().equals(schueler1.getKlasse()) && schueler2.getReligion().equals(schueler1.getReligion()))
								.map(Schueler::getName)
								.collect(Collectors.toList()));
				}
				
				religionsZugehoerigkeit.put(schueler.getReligion(), schuelerMap);
			}
		}
		
		return religionsZugehoerigkeit;
	}
	
	Map<LocalDate, Set<String>> getGeburtstagsListe (int jahr) {
		Map<LocalDate, Set<String>> geburtstagsListe = new TreeMap<>();
		
		for (Schueler schueler : schuelerCollection) {
			if (schueler.getGeboren().getYear() <= jahr && !geburtstagsListe.containsKey(schueler.getGeboren()))
				geburtstagsListe.put(LocalDate.of(jahr, schueler.getGeboren().getMonthValue(), schueler.getGeboren().getDayOfMonth()), schuelerCollection.stream()
						.filter(schueler1 -> schueler1.getGeboren().getMonthValue() == schueler.getGeboren().getMonthValue() && schueler1.getGeboren().getDayOfMonth() == schueler.getGeboren().getDayOfMonth())
						.map(schueler1 -> String.format("%s %s %s %d", schueler1.getName(), schueler1.getVorname(), schueler1.getKlasse(), jahr - schueler1.getGeboren().getYear()))
						.collect(Collectors.toCollection(TreeSet::new)));
		}
		
		return geburtstagsListe;
	}
	
	Map<LocalDate, Set<String>> getGeburtstagsListe () {
		return getGeburtstagsListe(LocalDate.now().getYear());
	}
}
