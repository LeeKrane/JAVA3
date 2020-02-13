package labors.labor06;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LeeKrane
 */

@SuppressWarnings("CanBeFinal")
public class UniqueCharacters {
	private static Map<String, Long> checkedStrings = new HashMap<>();
	
	private static long detectDifferentChars (String check) {
		return checkedStrings.computeIfAbsent(check, str -> str.chars().distinct().count());
	}
	
	public static void main (String[] args) {
		System.out.println(detectDifferentChars("FFFfF"));
		System.out.println(detectDifferentChars("!?"));
		System.out.println(detectDifferentChars("HahA"));
		System.out.println(detectDifferentChars("!?"));
		System.out.println(checkedStrings);
	}
}