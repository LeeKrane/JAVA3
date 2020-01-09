package labors.labor12;

import java.io.*;

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
		try (FileOutputStream fos = new FileOutputStream(filename)) {
			fos.write(output.getBytes());
		}
	}
	
	private static String readFromFile (AsciiInputStream inputStream, String filename, int exclude) throws IOException {
		StringBuilder builder = new StringBuilder();
		inputStream.skipLines(exclude);
		builder.append(inputStream.readLine());
		while (inputStream.available() > 0) {
			builder.append('\n').append(inputStream.readLine());
		}
		return builder.toString();
	}
	
	private static String readFromFile (AsciiInputStream inputStream, String filename, int excludeStart, int excludeEnd) throws IOException {
		if (excludeStart >= excludeEnd)
			return new String(inputStream.readAllBytes());
		StringBuilder builder = new StringBuilder();
		if (excludeStart > 0)
			builder.append(inputStream.readLine());
		for (int i = 1; i < excludeStart; i++)
			builder.append('\n').append(inputStream.readLine());
		inputStream.skipLines(excludeEnd - excludeStart);
		while (inputStream.available() > 0)
			builder.append('\n').append(inputStream.readLine());
		return builder.toString();
	}
}
