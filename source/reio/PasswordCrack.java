package reio;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.Instant;

public class PasswordCrack {
	/*
		hash-algorithm: SHA-256
		first (short time) hash: 9c8844145a7aeea05a0a8582e3f38b02e759061a0536d2e103607ac5f28b5d88
		first solution: !computationally&
		first time (ms): 273665
		second (long time) hash: b1435b304bf50e05853947ea8c0b5f19ac26bf5f8c8fe5f927b4a744c2443232
		second solution:
		second time (ms):
	 */
	
	public static void main (String[] args) {
		// first:
		//doBruteForce("source/reio/words.txt", new char[] {'!', '$', '%', '&', '/', '(', ')', '=', '?'}, "9c8844145a7aeea05a0a8582e3f38b02e759061a0536d2e103607ac5f28b5d88");
		// second:
		doBruteForce("source/reio/words.txt", new char[] {'!', '$', '%', '&', '/', '(', ')', '=', '?'}, "b1435b304bf50e05853947ea8c0b5f19ac26bf5f8c8fe5f927b4a744c2443232");
	}
	
	private static void doBruteForce (String filePath, char[] startAndEnd, String hash) {
		try {
			Instant start = Instant.now();
			System.out.println(bruteForce(filePath, startAndEnd, hash));
			Instant end = Instant.now();
			Duration d = Duration.between(start, end);
			System.out.format("passed time: %d milliseconds", d.toMillis());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	private static String bruteForce (String filePath, char[] startAndEnd, String hash) throws IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(new File(filePath)))) {
			String word;
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			while (br.ready()) {
				word = br.readLine();
				for (char c1 : startAndEnd) {
					for (char c2 : startAndEnd) {
						md.update((c1 + word + c2).getBytes());
						byte[] digest = md.digest();
						if (toHex(digest).equals(hash))
							return c1 + word + c2;
					}
				}
			}
			return "Error 404: password not found.";
		} catch (NoSuchAlgorithmException e) {
			System.err.println(e.getMessage());
			return "";
		}
	}
	
	private static String toHex (byte[] digest) {
		StringBuilder sb = new StringBuilder(digest.length * 2);
		for (byte b : digest)
			sb.append(String.format("%02x", b));
		return sb.toString();
	}
}
