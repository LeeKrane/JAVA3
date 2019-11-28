package labors.labor10;

import java.io.IOException;
import java.io.RandomAccessFile;

public class App {
	public static void main (String[] args) {
	
	}
	
	static void createFile (String filePath, double... values) throws IOException {
		try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw")) {
			raf.setLength(0);
			raf.writeInt(values.length);
			for (Double value : values)
				raf.writeDouble(value);
		}
	}
	
	static boolean isValidFile (String filePath) throws IOException {
		try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
			if (raf.length() % 8 != 4)
				return false;
			int controlInteger = raf.readInt();
			int counter = 0;
			while (raf.getFilePointer() < raf.length()) {
				raf.readDouble();
				counter++;
			}
			return controlInteger == counter;
		}
	}
	
	static String getFileInfo (String filePath) throws IOException {
		try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
			if (!isValidFile(filePath))
				return filePath + " is invalid\nInfinity -Infinity NaN";
			StringBuilder output = new StringBuilder();
			output.append("Saved values: ").append(raf.readInt()).append('\n');
			while (raf.getFilePointer() < raf.length())
				output.append(String.format("%.2f ", raf.readDouble()));
			return output.toString().substring(0, output.toString().length() - 1); // without the substring tests are failing, because there is a whitespace at the end of the string which is returned.
		}
	}
	
	static void append (String filePath, double toAppend) throws IOException {
		if (!isValidFile(filePath))
			throw new IllegalArgumentException(filePath + " is invalid");
		try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw")) {
			int i = raf.readInt();
			raf.seek(0);
			raf.writeInt(i + 1);
			raf.seek(raf.length());
			raf.writeDouble(toAppend);
		}
	}
}
