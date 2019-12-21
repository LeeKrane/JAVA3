package labors.labor10;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

/**
 * @author LeeKrane
 */

public class RAF {
	// 1 RandomAccessFile
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
				return filePath + " is invalid";
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
	
	// 2 Numbers
	private static boolean isValidFileNumbers (String filePath) throws IOException {
		try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
			while (raf.getFilePointer() < raf.length()) {
				try {
					if (raf.readByte() == 0)
						raf.readInt();
					else
						raf.readDouble();
				} catch (EOFException e) {
					return false;
				}
			}
			return raf.length() != 0 && raf.getFilePointer() == raf.length();
		}
	}
	
	static List<Number> getContents (String filePath) throws IOException {
		if (!isValidFileNumbers(filePath))
			throw new IllegalArgumentException(filePath + " is invalid");
		try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
			List<Number> numbers = new ArrayList<>();
			while (raf.getFilePointer() < raf.length()) {
				if (raf.readByte() == 0)
					numbers.add(raf.readInt());
				else
					numbers.add(raf.readDouble());
			}
			return numbers;
		}
	}
	
	static Map<String, Set<Number>> groupByType (List<? extends Number> numbers) {
		Map<String, Set<Number>> numberMap = new TreeMap<>();
		Set<Number> set;
		for (Number number : numbers) {
			String numberClass = number.getClass().getSimpleName();
			set = numberMap.getOrDefault(numberClass, new HashSet<>());
			set.add(number);
			numberMap.put(numberClass, set);
		}
		return numberMap;
	}
	
	/*
	static Map<String, Set<Number>> groupByType (List<? extends Number> numbers) {
		Map<String, Set<Number>> numberMap = new TreeMap<>();
		Set<Number> integerSet = new HashSet<>();
		Set<Number> doubleSet = new HashSet<>();
		for (Number number : numbers) {
			if (number instanceof Integer)
				integerSet.add(number);
			else if (number instanceof Double)
				doubleSet.add(number);
		}
		numberMap.put("Integer", integerSet);
		numberMap.put("Double", doubleSet);
		return numberMap;
	}
	 */
}
