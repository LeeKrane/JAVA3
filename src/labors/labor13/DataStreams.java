package labors.labor13;

import java.io.*;
import java.util.*;

/**
 * @author LeeKrane
 */

public class DataStreams {
	/* instructions:
	open terminal in project folder
	java src/labors/labor13/DataStreams.java res/labors/labor13/org.txt res/labors/labor13/bin.bin res/labors/labor13/rec.txt
	 */
	public static void main (String[] args) {
		if (args.length != 3)
			errorOutput(1, args);
		try {
			convertToBinary(args[0], args[1]);
			Queue<Character> types = new LinkedList<>();
			Map<String, Queue<Object>> data = readBinary(args[1], types);
			reconstructFile(args[2], data, types);
		} catch (IOException e) {
			System.err.println(e + ": " + e.getMessage());
		}
	}
	
	private static void errorOutput (int status, String[] args) {
		System.err.println("The following arguments are invalid:\n" + Arrays.toString(args) + "\nUse the following statement:\njava DataStreams <original_filename> <binary_filename> <reconstructed_filename>");
		System.exit(status);
	}
	
	private static void convertToBinary (String inputFilename, String outputFilename) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
			 DataOutputStream os = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(outputFilename)))) {
			while (reader.ready()) {
				String line = reader.readLine();
				if (writeInteger(line, os)) continue;
				if (writeDouble(line, os)) continue;
				writeBoolean(line, os);
			}
		}
	}
	
	private static void writeBoolean (String line, DataOutputStream os) throws IOException {
		switch (line) {
			case "true":
				os.writeChar('t');
				break;
			case "false":
				os.writeChar('f');
		}
	}
	
	private static boolean writeDouble (String line, DataOutputStream os) throws IOException {
		try {
			double input = Double.parseDouble(line);
			os.writeChar('d');
			os.writeDouble(input);
			return true;
		} catch (NumberFormatException ignored) {}
		return false;
	}
	
	private static boolean writeInteger (String line, DataOutputStream os) throws IOException {
		try {
			int input = Integer.parseInt(line);
			os.writeChar('i');
			os.writeInt(input);
			return true;
		} catch (NumberFormatException ignored) {}
		return false;
	}
	
	private static Map<String, Queue<Object>> readBinary (String filename, Queue<Character> types) throws IOException {
		try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)))) {
			Map<String, Queue<Object>> ret = new HashMap<>();
			Queue<Object> integers = new LinkedList<>();
			Queue<Object> doubles = new LinkedList<>();
			Queue<Object> booleans = new LinkedList<>();
			char ch;
			while (dis.available() > 0) {
				ch = dis.readChar();
				switch (ch) {
					case 'i':
						integers.add(dis.readInt());
						types.add('i');
						break;
					case 'd':
						doubles.add(dis.readDouble());
						types.add('d');
						break;
					case 't':
					case 'f':
						booleans.add(ch == 't');
						types.add(ch);
						break;
					default:
						throw new IllegalStateException();
				}
			}
			ret.put("Integer", integers);
			ret.put("Double", doubles);
			ret.put("Boolean", booleans);
			return ret;
		}
	}
	
	private static void reconstructFile (String filename, Map<String, Queue<Object>> data, Queue<Character> types) throws IOException {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
			while (!types.isEmpty()) {
				switch (types.poll()) {
					case 'i':
						writer.write(data.get("Integer").poll().toString());
						break;
					case 'd':
						writer.write(data.get("Double").poll().toString());
						break;
					case 't':
					case 'f':
						writer.write(data.get("Boolean").poll().toString());
						break;
					default:
						throw new IllegalStateException();
				}
				writer.newLine();
			}
		}
	}
}
