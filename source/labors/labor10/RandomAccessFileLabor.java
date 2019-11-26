package labors.labor10;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileLabor {
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
			return raf.length() % 8 == 4;
		}
	}
	
	static String getFileInfo (String filePath) throws IOException {
		try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
			StringBuilder output = new StringBuilder();
			output.append("Saved values: ").append(raf.readInt());
			while (raf.getFilePointer() < raf.length())
				output.append (String.format("%.2f ", raf.readDouble()));
			return output.toString();
		}
	}
	
	static void append (String filename, double toAppend) {
	
	}
}
