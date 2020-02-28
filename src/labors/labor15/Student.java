package labors.labor15;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Student implements Serializable {
	private String name;
	private String firstName;
	private transient Integer recognition;
	private transient Long socialSecurityNumber;
	private static Set<Integer> recognitions = new HashSet<>();
	private static final int KEY = 5;
	
	public Student () {}
	
	public Student (String name, String firstName, Integer recognition, Long socialSecurityNumber) {
		if (recognitions.contains(recognition))
			throw new IllegalArgumentException("The given recognition is already taken!");
		if (!validSocialSecurityNumber(socialSecurityNumber))
			throw new IllegalArgumentException("The given social security number is invalid!");
		this.name = name;
		this.firstName = firstName;
		this.recognition = recognition;
		this.socialSecurityNumber = socialSecurityNumber;
		recognitions.add(recognition);
	}
	
	private static boolean validSocialSecurityNumber (long socialSecurityNumber) {
		String str = Long.toString(socialSecurityNumber);
		if (str.length() != 10 || Integer.parseInt(str.substring(0, 3)) % 11 == 10)
			return false;
		
		try {
			LocalDate.parse(str.substring(4, 6) + '-' + str.substring(6, 8) + "-19" + str.substring(8), DateTimeFormatter.ofPattern("dd-MM-uuuu"));
		} catch (DateTimeParseException e) {
			return false;
		}
		
		int check = str.charAt(3) - '0';
		long sum = 0;
		int[] mul = new int[]{3, 7, 9, 0, 5, 8, 4, 2, 1, 6};
		for (int i = 0; i < str.length(); i++) {
			sum += (str.charAt(i) - '0') * mul[i];
		}
		sum %= 11;
		return check == sum;
	}
	
	public static void main (String[] args) {
		byte[] bytes = new byte[]{};
		List<Student> students = List.of(
				new Student("Doe", "Joe", 1234, 1237010180L),
				new Student("Doe", "Jane", 4321, 1237010180L),
				new Student("Dane", "Joe", 5678, 1237010180L),
				new Student("Dane", "Jane", 8765, 1237010180L),
				new Student("Dane", "John", 9876, 1237010180L)
		);
		List<Student> reconstructed = new ArrayList<>();
		
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
			 ObjectOutputStream os = new ObjectOutputStream(bos)) {
			for (Student student : students)
				os.writeObject(student);
			
			try (ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
				 ObjectInputStream is = new ObjectInputStream(bis)) {
				while (bis.available() > 0) {
					Student s = (Student) is.readObject();
					reconstructed.add(s);
				}
			}
			
			System.out.println(students);
			System.out.println(reconstructed);
		} catch (IOException | ClassNotFoundException e) {
			System.err.println(e + ": " + e.getMessage());
		}
	}
	
	private static String caesar (String e, int key) {
		StringBuilder builder = new StringBuilder();
		for (char c : e.toCharArray()) {
			if (c >= 'A' && c <= 'J') {
				c += key;
				if (c < 'A') c += 10;
				else if (c > 'J') c -= 10;
				c += '0' - 'A';
			} else {
				c += key;
				if (c < '0') c += 10;
				else if (c > '9') c -= 10;
				c += 'A' - '0';
			}
			builder.append(c);
		}
		return builder.toString();
	}
	
	private void writeObject (ObjectOutputStream os) throws IOException {
		os.writeUTF(name);
		os.writeUTF(firstName);
		os.writeUTF(caesar(Integer.toString(recognition), KEY));
		os.writeUTF(caesar(Long.toString(socialSecurityNumber), KEY));
	}
	
	private void readObject (ObjectInputStream is) throws IOException {
		name = is.readUTF();
		firstName = is.readUTF();
		recognition = Integer.parseInt(caesar(is.readUTF(), -KEY));
		socialSecurityNumber = Long.parseLong(caesar(is.readUTF(), -KEY));
	}
	
	@Override
	public String toString () {
		return "Student{" +
				"name='" + name + '\'' +
				", firstName='" + firstName + '\'' +
				", recognition=" + recognition +
				", socialSecurityNumber=" + socialSecurityNumber +
				'}';
	}
}
