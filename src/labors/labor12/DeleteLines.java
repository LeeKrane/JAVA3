package labors.labor12;

import java.io.*;
import java.util.*;

/**
 * @author LeeKrane
 */

public class DeleteLines {
	public static void main (String[] args) {
		Set<Integer[]> ranges = new HashSet<>();
		if (args.length == 2) {
			try (AsciiInputStream is = new AsciiInputStream(args[0])) {
				export(args[1], new String(is.readAllBytes()));
			} catch (FileNotFoundException e) {
				System.err.println("The file " + args[0] + " was not found.");
			} catch (IOException e) {
				System.err.println(e + ": " + e.getMessage());
			}
			return;
		}
		for (int i = 2; i < args.length; i++) {
			if (args[i].matches("\\d+-\\d+") || args[i].matches("\\d+")) {
				if (Integer.parseInt(args[i].split("-")[0]) < (args[i].matches("\\d+-\\d+") ? Integer.parseInt(args[i].split("-")[1]) : Integer.parseInt(args[i].split("-")[0]) + 1))
					ranges.add(createRange(args[i]));
			} else errorOutput(1);
		}
		try (AsciiInputStream is = new AsciiInputStream(args[0])) {
			export(args[1], deleteLines(is, ranges));
		} catch (FileNotFoundException e) {
			System.err.println("The file " + args[0] + " was not found.");
		} catch (IOException e) {
			System.err.println(e + ": " + e.getMessage());
		}
	}
	
	private static Integer[] createRange (String input) {
		Integer[] range = new Integer[2];
		if (input.matches("\\d+")) {
			range[0] = Integer.parseInt(input);
			range[1] = range[0] + 1;
		}
		else if (input.matches("\\d+-\\d+")) {
			range[0] = Integer.parseInt(input.split("-")[0]);
			range[1] = Integer.parseInt(input.split("-")[1]) + 1;
		}
		else
			throw new IllegalArgumentException();
		return range;
	}
	
	private static int maxEnd (Set<Integer[]> ranges) {
		int max = 0;
		for (Integer[] range : ranges) {
			if (range[1] > max)
				max = range[1];
		}
		return max;
	}
	
	private static boolean inRange (int value, Set<Integer[]> ranges) {
		for (Integer[] range : ranges) {
			if (inRange(value, range))
				return true;
		}
		return false;
	}
	
	private static boolean inRange (int value, Integer[] range) {
		return value >= range[0] && value < range[1];
	}
	
	private static String deleteLines (AsciiInputStream is, Set<Integer[]> ranges) throws IOException {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i <= maxEnd(ranges); i++) {
			if (inRange(i, ranges))
				is.skipLine();
			else
				builder.append(is.readLine());
		}
		builder.append(new String(is.readAllBytes()));
		return builder.toString();
	}
	
	private static void export (String filename, String output) throws IOException {
		try (FileOutputStream os = new FileOutputStream(filename)) {
			os.write(output.getBytes());
		}
	}
	
	private static void errorOutput (int status) {
		System.err.println("Error: wrong input.\nArguments: <input filename> <output filename> <<startLine-endLine>|<line>>+");
		System.exit(status);
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