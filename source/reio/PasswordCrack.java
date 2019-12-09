package reio;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;

public class PasswordCrack {
	private static char[] specialCharacters = new char[] {'!', '$', '%', '&', '/', '(', ')', '=', '?'};
	
	/**
	 * hashing-algorithm: SHA-256
	 * <p>
	 * first hashcode: 9c8844145a7aeea05a0a8582e3f38b02e759061a0536d2e103607ac5f28b5d88
	 * solution: !computationally&
	 * time in ms on remote: 234659
	 * time in ms on laptop: 273665
	 * time in ms on pc: 168847
	 * <p>
	 * second hashcode: b1435b304bf50e05853947ea8c0b5f19ac26bf5f8c8fe5f927b4a744c2443232
	 * solution: )secundoprimary!
	 * time in ms on remote: 1061221
	 * time in ms on laptop: 1233569
	 * time in ms on pc: 838803
	 */
	public static void main (String[] args) {
		/* first hashcode */
		initiateBruteForce("source/reio/words.txt", "9c8844145a7aeea05a0a8582e3f38b02e759061a0536d2e103607ac5f28b5d88");
		/* second hashcode */
		initiateBruteForce("source/reio/words.txt", "b1435b304bf50e05853947ea8c0b5f19ac26bf5f8c8fe5f927b4a744c2443232");
	}
	
	private static void initiateBruteForce (String filePath, String hashcode) {
		try {
			Instant start = Instant.now();
			System.out.println(bruteForce(filePath, hashcode));
			Instant end = Instant.now();
			System.out.format("time in milliseconds: %d\n", Duration.between(start, end).toMillis());
		} catch (IOException | NoSuchAlgorithmException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private static String bruteForce (String filePath, String hashcode) throws IOException, NoSuchAlgorithmException {
		try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			String word;
			while (br.ready()) {
				word = br.readLine();
				for (char c1 : specialCharacters) {
					for (char c2 : specialCharacters) {
						md.update((c1 + word + c2).getBytes());
						byte[] digest = md.digest();
						if (toHex(digest).equals(hashcode))
							return c1 + word + c2;
					}
				}
			}
		}
		throw new InvalidHashcodeException();
	}
	
	private static String toHex (byte[] digest) {
		StringBuilder sb = new StringBuilder(digest.length * 2);
		for (byte b : digest)
			sb.append(String.format("%02x", b));
		return sb.toString();
	}
}
