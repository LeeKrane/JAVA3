package labors.labor08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author LeeKrane
 */


public class Diceware {
	public static void main (String[] args) {
		try {
			System.out.println(getRandomPassword());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private static String getRandomPassword () throws IOException {
		Map<Integer, String> passwordMap = getDiceWarePairs("res/labors/labor08/diceware_german.txt");
		Random random = new Random();
		String[] generatedWords = new String[5];
		String newGeneratedWord;
		int newGeneratedWordIndex;
		
		for (int i = 0; i < generatedWords.length; i++) {
			newGeneratedWordIndex = 0;
			
			for (int j = 0; j < 5; j++) {
				newGeneratedWordIndex *= 10;
				newGeneratedWordIndex += Math.abs(random.nextInt() % 6) + 1;
			}
			newGeneratedWord = passwordMap.get(newGeneratedWordIndex);
			generatedWords[i] = newGeneratedWord;
		}
		
		StringBuilder returnString = new StringBuilder();
		for (int i = 0; i < generatedWords.length; i++) {
			returnString.append(generatedWords[i]);
			if (i != generatedWords.length - 1) returnString.append(" ");
		}
		
		return returnString.toString();
	}
	
	private static Map<Integer, String> getDiceWarePairs (String filePath) throws IOException {
		return Files.lines(Paths.get(filePath))
				.map(Pattern.compile("(\\d{5})\\s(.+)")::matcher)
				.filter(Matcher::find)
				.collect(Collectors.toMap(i -> Integer.parseInt(i.group(1)), s -> s.group(2)));
	}
	
	// non-fancy variant:
	public static Map<Integer, String> getDiceWarePairs2 (String filePath) throws IOException {
		Map<Integer, String> diceWarePairs = new HashMap<>();
		BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
		String line;
		String[] split;
		
		while (br.ready()) {
			line = br.readLine();
			
			if (line.matches("(\\d{5})\\s(.+)")) {
				split = line.split("\\s");
				diceWarePairs.put(Integer.parseInt(split[0]), split[1]);
			}
		}
		
		return diceWarePairs;
	}
}