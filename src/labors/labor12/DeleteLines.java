package labors.labor12;

import java.io.*;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author LeeKrane
 */

// TODO: refactor

public class DeleteLines {
	public static void main (String[] args) {
		if (!args[3].matches("(\\d+-\\d+)|\\d+"))
			System.err.println("Error: wrong input.\nCommand: " + DeleteLines.class.getSimpleName() + " <input filename> <output filename> <startLine-endLine>|<endLine>");
		else {
			int[] excludeStarts = new int[args.length - 3];
			int[] excludeEnds = new int[args.length - 3];
			String[] split;
			for (int i = 3; i < args.length; i++) {
				if (args[i].matches("\\d+-\\d+")) {
					split = args[i].split("-");
					excludeStarts[i - 3] = Integer.parseInt(split[0]);
					excludeEnds[i - 3] = Integer.parseInt(split[1]);
				}
				else {
					excludeStarts[i - 3] = Integer.parseInt(args[i]);
					excludeEnds[i - 3] = Integer.parseInt(args[i]);
				}
			}
			try (AsciiInputStream is = new AsciiInputStream(args[1])) {
				export(args[2], readFromFile(is, getExcludedLines(excludeStarts, excludeEnds)));
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
	
	private static String readFromFile (AsciiInputStream inputStream, Set<Integer> excludes) throws IOException {
		if (excludes.size() == 0)
			return new String(inputStream.readAllBytes());
		StringBuilder builder = new StringBuilder();
		int lastDeleted = 0;
		if (!excludes.contains(0)) {
			builder.append(inputStream.readLine());
			lastDeleted = 1;
		}
		for (int i = lastDeleted; i <= getMax(excludes); i++) {
			if (inputStream.available() == 0)
				break;
			if (excludes.contains(i))
				inputStream.skipLines(1);
			else
				builder.append(inputStream.readLine()).append('\n');
		}
		builder.append(new String(inputStream.readAllBytes()));
		return builder.toString();
	}
	
	private static int getMax (Set<Integer> set) {
		int max = 0;
		for (Integer i : set) {
			if (i > max)
				max = i;
		}
		return max;
	}
	
	private static Set<Integer> getExcludedLines (int[] excludeStarts, int[] excludeEnds) {
		Set<Integer> excludedLines = new TreeSet<>();
		for (int i = 0; i < excludeStarts.length; i++) {
			for (int j = excludeStarts[i]; j <= excludeEnds[i]; j++)
				excludedLines.add(j);
		}
		return excludedLines;
	}
}

class AsciiInputStream extends FileInputStream {
	public static void main (String[] args) {
		DeleteLines.main(new String[]{"DeleteLines", "res/labors/labor12/input.txt", "res/labors/labor12/output.txt", "1-2", "4", "5", "8"});
	}
	
	public AsciiInputStream (String name) throws FileNotFoundException {
		super(name);
	}
	
	public AsciiInputStream (File file) throws FileNotFoundException {
		super(file);
	}
	
	public String readLine () throws IOException {
		StringBuilder builder = new StringBuilder();
		int character;
		while ((character = read()) != -1) {
			if (character == '\n')
				break;
			builder.append((char) character);
		}
		return builder.toString();
	}
	
	public void skipLines (long toSkip) throws IOException {
		for (int i = 0; i < toSkip; i++)
			readLine();
	}
}