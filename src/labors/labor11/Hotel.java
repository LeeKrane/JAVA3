package labors.labor11;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author LeeKrane
 */

public class Hotel implements Comparable<Hotel> {
	private String name; // 64 bytes
	private String location; // 64 bytes
	private int size; // 4 bytes
	private boolean smoking; // 1 bytes
	private long rate; // 8 bytes
	private LocalDate date; // 10 bytes
	private String owner; // 8 bytes
	// 159 bytes in total
	
	Hotel (byte[] data, Map<String, Short> properties) {
		if (data == null || properties == null) throw new IllegalArgumentException("data/properties must not be null!");
		try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(data)))) {
			name = new String(dis.readNBytes(properties.get("name"))).trim();
			location = new String(dis.readNBytes(properties.get("location"))).trim();
			size = Integer.parseInt(new String(dis.readNBytes(properties.get("size"))).trim());
			smoking = getBoolean(new String(dis.readNBytes(properties.get("smoking"))).trim());
			rate = (long) Double.parseDouble(new String(dis.readNBytes(properties.get("rate"))).trim().substring(1)) * 100;
			date = LocalDate.parse(new String(dis.readNBytes(properties.get("date"))).trim(), DateTimeFormatter.ofPattern("uuuu/MM/dd"));
			owner = new String(dis.readNBytes(properties.get("owner"))).trim();
		} catch (IOException e) {
			throw new IllegalArgumentException("Given data is invalid.");
		}
	}
	
	public Hotel (String name, String location, int size, boolean smoking, long rate, LocalDate date, String owner) {
		this.name = name;
		this.location = location;
		this.size = size;
		this.smoking = smoking;
		this.rate = rate;
		this.date = date;
		this.owner = owner;
	}
	
	static Map<String, Short> readProperties (String filePath) {
		try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {
			Map<String, Short> properties = new LinkedHashMap<>();
			dis.skipBytes(8); // skip ID & offset
			short columns = dis.readShort();
			for (short s = 0; s < columns; s++)
				properties.put(dis.readUTF(), dis.readShort());
			return properties;
		} catch (IOException e) {
			throw new IllegalArgumentException(filePath + " is invalid.");
		}
	}
	
	static Set<Hotel> readHotels (String filePath) throws IOException {
		final int VALID = 0x0000;
		final int INVALID = 0x8000;
		try (DataInputStream dis = new DataInputStream(new FileInputStream(filePath))) {
			Map<String, Short> properties = readProperties(filePath);
			Set<Hotel> hotels = new TreeSet<>();
			int propertySizeSum = getPropertySizeSum(properties);
			dis.skipBytes(4); // skip ID
			dis.skipBytes(dis.readInt() - 8); // skip properties
			while (dis.available() >= propertySizeSum + 2) {
				int deleted = dis.readUnsignedShort();
				switch (deleted) {
					case VALID:
						//System.out.println(new String(dis.readNBytes(propertySizeSum)));
						hotels.add(new Hotel(dis.readNBytes(propertySizeSum), properties));
						break;
					case INVALID:
						dis.skipBytes(propertySizeSum);
						break;
					default:
						throw new IllegalArgumentException(filePath + " is invalid.");
				}
			}
			return hotels;
		}
	}
	
	byte[] getBytes (Map<String, Short> properties) throws IOException {
		try (ByteArrayOutputStream os = new ByteArrayOutputStream(getPropertySizeSum(properties))) {
			os.write(fillWithWhitespaces(name, properties.get("name")).getBytes());
			os.write(fillWithWhitespaces(location, properties.get("location")).getBytes());
			os.write(fillWithWhitespaces(Integer.toString(size), properties.get("size")).getBytes());
			os.write((smoking ? "Y" : "N").getBytes());
			os.write("$".getBytes());
			os.write(fillWithWhitespaces(String.format("%.2f", ((double) rate / 100)).replace(',', '.'), properties.get("rate") - "$".getBytes().length).getBytes());
			os.write(fillWithWhitespaces(String.format("%04d/%02d/%02d", date.getYear(), date.getMonthValue(), date.getDayOfMonth()), properties.get("date")).getBytes());
			os.write(fillWithWhitespaces(owner, properties.get("owner")).getBytes());
			return os.toByteArray();
		}
	}
	
	static String fillWithWhitespaces (String fill, int length) {
		return fill + " ".repeat(Math.max(0, length - fill.length()));
	}
	
	static int getStartingOffset (String filePath) throws IOException {
		try (DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)))) {
			dis.skipBytes(4); // skip ID
			return dis.readInt(); // return offset
		}
	}
	
	static int getPropertySizeSum (Map<String, Short> properties) {
		short sum = 0;
		for (Short s : properties.values())
			sum += s;
		return sum;
	}
	
	public String getName () {
		return name;
	}
	
	public String getLocation () {
		return location;
	}
	
	@Override
	public int compareTo (Hotel o) {
		return Comparator.comparing(Hotel::getLocation).thenComparing(Hotel::getName).compare(this, o);
	}
	
	private static boolean getBoolean (String value) {
		return value.equals("Y");
	}
	
	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Hotel hotel = (Hotel) o;
		return size == hotel.size &&
				smoking == hotel.smoking &&
				Double.compare(hotel.rate, rate) == 0 &&
				Objects.equals(name, hotel.name) &&
				Objects.equals(location, hotel.location) &&
				Objects.equals(date, hotel.date) &&
				Objects.equals(owner, hotel.owner);
	}
	
	@Override
	public int hashCode () {
		return Objects.hash(name, location, size, smoking, rate, date, owner);
	}
	
	@Override
	public String toString () {
		return "Hotel{" +
				"name='" + name + '\'' +
				", location='" + location + '\'' +
				", size=" + size +
				", smoking=" + smoking +
				", rate=" + rate +
				", date=" + date +
				", owner='" + owner + '\'' +
				'}';
	}
}
