package labors.labor06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author LeeKrane
 */

public class Firewall {
	public static void main (String[] args) {
		try {
			Map<String, Set<String>> adresses = Files.lines(Paths.get("resources/labors/labor06/firewall.txt")) // ein Stream wird geöffnet
					.map(Pattern.compile("([.0-9]*)[\\s]*([.0-9]*).*")::matcher) // erstellt einen Matcher mit einem Regex
					.filter(Matcher::find) // wirft alle Zeilen die nicht dem Regex entsprechen raus
					.collect(Collectors.groupingBy(s -> s.group(2), Collectors.mapping(s -> s.group(1), Collectors.toSet()))); // Gruppiert nach Zieladressen (key) und verwandelt die Quelladressen in ein Set (value)
			System.out.println(adresses);
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
	}
}