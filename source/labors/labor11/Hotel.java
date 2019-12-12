package labors.labor11;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Hotel {
	private String name;
	private String location;
	private int size;
	private boolean smoking;
	private double rate;
	private LocalDate date;
	private String owner = new String();
	
	Hotel (byte[] properties) {
	
	}
	
	static Map<String, Short> readProperties (String filePath) {
		try (DataInputStream dis = new DataInputStream(new FileInputStream(filePath))) {
			Map<String, Short> properties = new LinkedHashMap<>();
			dis.skipBytes(8);
			short columns = dis.readShort();
			for (short s = 0; s < columns; s++)
				properties.put(dis.readUTF(), dis.readShort());
			return properties;
		} catch (IOException e) {
			return null;
		}
	}
	
	static Set<Hotel> readHotels (String filePath) throws IOException {
		try (DataInputStream dis = new DataInputStream(new FileInputStream(filePath))) {
			Map<String, Short> properties = readProperties(filePath);
			int propertySizeSum = 0;
			for (Short s : properties.values()) {
				propertySizeSum += s;
			}
			Set<Hotel> hotels = new LinkedHashSet<>();
			dis.skipBytes(4);
			dis.skipBytes(dis.readInt() - 8);
			while (dis.available() >= propertySizeSum + 2) {
				short deleted = dis.readShort();
				if (deleted == 0x0000) {
					// ...
				}
				else if (deleted == 0x8000)
					dis.skipBytes(propertySizeSum);
				else
					throw new IllegalArgumentException(filePath + " is invalid.");
			}
			return hotels;
		}
	}
	
	static int getStartingOffset (String filePath) throws IOException {
		return 0;
	}
}
