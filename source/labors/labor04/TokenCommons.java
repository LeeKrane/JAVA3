package labors.labor04;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * @author LeeKrane
 */

public class TokenCommons {
	public static void main (String[] args) {
		try {
			Collection<String> file1 = getTokens("resources/labors/labor04/tokens1.txt");
			Collection<String> file2 = getTokens("resources/labors/labor04/tokens2.txt");
			file1.retainAll(file2);
			Set<String> commons = new TreeSet<>(file1);
			System.out.println(commons);
		} catch (FileNotFoundException fnfe) {
			System.err.println(fnfe.getMessage());
		}
	}
	
	private static Collection<String> getTokens (String filename) throws FileNotFoundException {
		Scanner scanner = new Scanner(new BufferedReader(new FileReader(new File(filename))));
		Collection<String> tokens = new LinkedList<>();
		String[] tokensPerLine;
		
		while (scanner.hasNextLine()) {
			tokensPerLine = scanner.nextLine().split("[\\s,.:;?!]+");
			Collections.addAll(tokens, tokensPerLine);
		}
		
		return tokens;
	}
}
