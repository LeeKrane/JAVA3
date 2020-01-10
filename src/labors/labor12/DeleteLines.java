package labors.labor12;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author LeeKrane
 */

public class DeleteLines {
	public static void main (String[] args) {
		Set<Integer> excludedLines = new TreeSet<>();
		String[] split;
		for (int i = 2; i < args.length; i++) {
			if (args[i].matches("\\d+-\\d+")) {
				split = args[i].split("-");
				for (int j = Integer.parseInt(split[0]); j <= Integer.parseInt(split[1]); j++)
					excludedLines.add(j);
			}
			else if (args[i].matches("\\d+"))
				excludedLines.add(Integer.parseInt(args[i]));
			else
				wrongInputErrorDisplay();
			try (AsciiInputStream inputStream = new AsciiInputStream(args[0])) {
				export(args[1], deleteLines(inputStream, excludedLines));
			} catch (FileNotFoundException e) {
				System.err.println("The file " + args[0] + " was not found.");
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
		if (args.length == 2) {
			try (AsciiInputStream inputStream = new AsciiInputStream(args[0])) {
				export(args[1], new String(inputStream.readAllBytes()));
			} catch (FileNotFoundException e) {
				System.err.println("The file " + args[0] + " was not found.");
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	private static String deleteLines (AsciiInputStream inputStream, Set<Integer> excludedLines) throws IOException {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i <= maxValue(excludedLines); i++) {
			if (excludedLines.contains(i))
				inputStream.skipLine();
			else
				builder.append(inputStream.readLine());
		}
		builder.append(new String(inputStream.readAllBytes()));
		return builder.toString();
	}
	
	private static void export (String filename, String toExport) throws IOException {
		try (FileOutputStream outputStream = new FileOutputStream(filename)) {
			outputStream.write(toExport.getBytes());
		}
	}
	
	private static int maxValue (Set<Integer> integers) {
		int maxValue = 0;
		for (Integer i : integers) {
			if (i > maxValue)
				maxValue = i;
		}
		return maxValue;
	}
	
	private static void wrongInputErrorDisplay () {
		System.err.println("Error: wrong input.\nArguments: <input filename> <output filename> <<startLine-endLine>|<line>>+");
		System.exit(1);
	}
}

class AsciiInputStream extends FileInputStream {
	public AsciiInputStream (String name) throws FileNotFoundException {
		super(name);
	}
	
	String readLine () throws IOException {
		StringBuilder builder = new StringBuilder();
		int character;
		while ((character = read()) != -1) {
			builder.append((char) character);
			if (character == '\n')
				break;
		}
		return builder.toString();
	}
	
	void skipLine () throws IOException {
		readLine();
	}
	
	void skipLines (long toSkip) throws IOException {
		for (int i = 0; i < toSkip; i++)
			readLine();
	}
}