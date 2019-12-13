package labors.labor11;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

// TODO:
//  read hotels without hardcoded property lengths
//  refactor
//  write tests


public class Hotel implements Comparable<Hotel> {
	private String name; // 64 byte
	private String location; // 64 byte
	private int size; // 4 byte
	private boolean smoking; // 1 byte
	private long rate; // 8 byte
	private LocalDate date; // 10 byte
	private String owner; // 8 byte
	
	Hotel (byte[] data, Map<String, Short> properties) {
	
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
		try (DataInputStream dis = new DataInputStream(new FileInputStream(filePath))) {
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
		try (DataInputStream dis = new DataInputStream(new FileInputStream(filePath))) {
			Map<String, Short> properties = readProperties(filePath);
			int propertySizeSum = 0;
			for (Short s : properties.values())
				propertySizeSum += s;
			Set<Hotel> hotels = new TreeSet<>();
			dis.skipBytes(4);
			dis.skipBytes(dis.readInt() - 8);
			while (dis.available() >= propertySizeSum + 2) {
				int deleted = dis.readUnsignedShort();
				switch (deleted) {
					case 0x0000:
						hotels.add(new Hotel(
								new String(dis.readNBytes(64)).trim(),
								new String(dis.readNBytes(64)).trim(),
								Integer.parseInt(new String(dis.readNBytes(4)).trim()),
								getBoolean(new String(dis.readNBytes(1)).trim()),
								(long) Double.parseDouble(new String(dis.readNBytes(8)).trim().substring(1)) * 100,
								LocalDate.parse(new String(dis.readNBytes(10)).trim(), DateTimeFormatter.ofPattern("uuuu/MM/dd")),
								new String(dis.readNBytes(8)).trim()
						));
						break;
					case 0x8000:
						dis.skipBytes(propertySizeSum);
						break;
					default:
						throw new IllegalArgumentException(filePath + " is invalid.");
				}
			}
			return hotels;
		}
	}
	
	static int getStartingOffset (String filePath) throws IOException {
		try (DataInputStream dis = new DataInputStream(new FileInputStream(filePath))) {
			dis.skipBytes(4);
			return dis.readInt();
		}
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
