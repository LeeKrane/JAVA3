package labors.labor07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author LeeKrane
 */

@SuppressWarnings({"SameParameterValue", "unused", "CanBeFinal"})
public class WasserstandAnalyse {
	private Map<LocalDateTime, Integer> levels;
	private static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
	
	public static void main (String[] args) {
		WasserstandAnalyse wa = new WasserstandAnalyse();
		
		LocalDateTime startingDate = LocalDateTime.parse("15.06.2009 00:00", df); // 1st entry
		LocalDateTime testEndingDate = LocalDateTime.parse("15.06.2009 05:45", df); // 70th entry
		LocalDateTime actualEndingDate = LocalDateTime.parse("13.07.2009 23:55", df); // last entry
		
		try {
			wa.levels = readFromFile("resources/labors/labor07/data_25268_W_MONTH.txt");
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		System.out.println("Alle Wasserstände:\n" + wa.levels + '\n');
		System.out.println("Alle Zeitpunkte mit dem höchsten Wasserstand der ersten 70 Einträge:\n" + wa.highest(startingDate, testEndingDate) + '\n'); // 221
		System.out.println("Durchschnittlicher Wasserstand der ersten 70 Einträge: " + wa.average(startingDate, testEndingDate) + '\n'); // 220.3913043478261
		System.out.println("Alle Zeitpunkte mit dem höchsten Wasserstand aller Einträge:\n" + wa.highest(startingDate, actualEndingDate) + '\n'); // 536
		System.out.println("Durchschnittlicher Wasserstand aller Einträge: " + wa.average(startingDate, actualEndingDate) + '\n'); // 381.5938210992696
	}
	
	private static Map<LocalDateTime, Integer> readFromFile (String filename) throws IOException {
		return Files.lines(Paths.get(filename))
				.map(Pattern.compile("((\\d{2}\\.){2}\\d{4}\\s\\d{2}:\\d{2})\\s((\\d{2}\\.){2}\\d{4}\\s\\d{2}:\\d{2})\\s(\\d{3})")::matcher)
				.filter(Matcher::find)
				.collect(Collectors.toMap(ldt -> LocalDateTime.parse(ldt.group(1), df), i -> Integer.parseInt(i.group(5))));
	}
	
	// non-fancy variant:
	private static Map<LocalDateTime, Integer> readFromFile2 (String filePath) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
		String line;
		String[] split;
		Map<LocalDateTime, Integer> map = new TreeMap<>();
		
		while (br.ready()) {
			line = br.readLine();
			if (line.matches("((\\d{2}\\.){2}\\d{4}\\s\\d{2}:\\d{2})\\s((\\d{2}\\.){2}\\d{4}\\s\\d{2}:\\d{2})\\s(\\d{3})\\s\\w\\s\\w{10}")) { // Regex ist anders als bei der Fancy Variante
				split = line.split("\\s");
				map.put(LocalDateTime.parse(split[0] + " " + split[1], df), Integer.parseInt(split[4]));
			}
		}
		return map;
	}
	
	private Map<LocalDateTime, Integer> highest (LocalDateTime from, LocalDateTime to) {
		SortedMap<LocalDateTime, Integer> subLevels = new TreeMap<>(levels).subMap(from, to);
		Map<LocalDateTime, Integer> maxValueDates = new TreeMap<>();
		int maxValue = 0;
		
		for (int value : subLevels.values()) {
			if (value > maxValue) maxValue = value;
		}
		// noinspection rawtypes
		for (Map.Entry mapEntry : subLevels.entrySet()) {
			if ((int) mapEntry.getValue() == maxValue)
				maxValueDates.put((LocalDateTime) mapEntry.getKey(), (int) mapEntry.getValue());
		}
		return maxValueDates;
	}
	
	private double average (LocalDateTime from, LocalDateTime to) {
		SortedMap<LocalDateTime, Integer> subLevels = new TreeMap<>(levels).subMap(from, to);
		double averageValue = 0;
		for (int i : subLevels.values()) averageValue += i;
		return averageValue / subLevels.size();
	}
}