package labors.labor12;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author LeeKrane
 */

public class DeleteLines {
	public static void main (String[] args) {
		if (!args[3].matches("(\\d+-\\d+)|\\d+"))
			System.err.println("Error: wrong input.\nCommand: " + DeleteLines.class.getSimpleName() + " <input filename> <output filename> <startLine-endLine>|<endLine>");
		else {
			try (AsciiInputStream is = new AsciiInputStream(args[1])) {
				if (args[3].matches("\\d+-\\d+")) {
					String[] split = args[3].split("-");
					export(args[2], readFromFile(is, args[1], Integer.parseInt(split[0]), Integer.parseInt(split[1])));
				}
				else
					export(args[2], readFromFile(is, args[1], Integer.parseInt(args[3])));
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	private static void export (String filename, String output) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			writer.write(output);
		}
	}
	
	private static String readFromFile (AsciiInputStream inputStream, String filename, int notIncludeEnd) throws IOException {
		return readFromFile(inputStream, filename, 0, notIncludeEnd);
	}
	
	private static String readFromFile (AsciiInputStream inputStream, String filename, int notIncludeStart, int notIncludeEnd) throws IOException {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < notIncludeStart; i++)
			builder.append(inputStream.readLine()).append('\n');
		inputStream.skipLines(notIncludeEnd - notIncludeStart);
		while (inputStream.available() > 0)
			builder.append(inputStream.readLine()).append('\n');
		return builder.toString();
	}
}
