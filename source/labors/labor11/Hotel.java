package labors.labor11;

import java.io.*;
import java.util.*;

public class Hotel {
	Hotel (byte[] properties) {
	
	}
	
	static Map<String, Short> readProperties (String filePath) {
		Map<String, Short> properties = new LinkedHashMap<>();
		try (DataInputStream dis = new DataInputStream(new FileInputStream(filePath))) {
			dis.readInt();
			dis.readInt();
			short columnCount = dis.readShort(), columnWidth, columnNameSize;
			byte[] columnName;
			for (short s = 0; s < columnCount; s++) {
				columnNameSize = dis.readShort();
				columnName = new byte[columnNameSize];
				if (dis.read(columnName) == -1) return null;
				columnWidth = dis.readShort();
				properties.put(new String(columnName), columnWidth);
			}
		} catch (IOException e) {
			return null;
		}
		return properties;
	}
	
	static Set<Hotel> readHotels (String filePath) throws IOException {
		return null;
	}
	
	static int getStartingOffset (String filePath) throws IOException {
		return 0;
	}
}
