package labors.labor11;

import java.io.*;
import java.util.*;

public class Hotel {
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
		return null;
	}
	
	static int getStartingOffset (String filePath) throws IOException {
		return 0;
	}
}
