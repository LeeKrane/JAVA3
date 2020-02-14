package labors.bonus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Steps {
	public static void main (String[] args) {
		List<Pair> unlocks = new ArrayList<>();
		List<Character> available = new ArrayList<>();
		List<Character> order = new ArrayList<>();
		String line, nextLine = null;
		try (BufferedReader reader = new BufferedReader(new FileReader("res/labors/bonus/test.txt"))) {
			if (reader.ready())
				nextLine = reader.readLine();
			while (reader.ready()) {
				line = nextLine;
				nextLine = reader.readLine();
				Pair p = new Pair(line.charAt(36), line.charAt(5));
				if (unlocks.contains(p)) {
					for (Pair pair : unlocks) {
						if (pair.equals(p))
							pair.add(p.required);
					}
				}
				else
					unlocks.add(p);
			}
			System.out.println(unlocks);
			order.add(unlocks.get(0).required.get(0));
			while (unlocks.size() > 0) {
				for (int i = 0; i < unlocks.size(); i++) {
					if (contains(order, unlocks.get(i).required)) {
						available.add(unlocks.get(i).unlocked);
						unlocks.remove(i);
						i--;
					}
				}
				available.sort(Comparator.naturalOrder());
				order.add(available.get(0));
				available.remove(0);
			}
			output(order);
		} catch (IOException e) {
			System.err.println(e + ": " + e.getMessage());
		} catch (IndexOutOfBoundsException e) {
			output(order);
		}
	}
	
	private static void output (List<Character> order) {
		StringBuilder builder = new StringBuilder();
		for (char c : order)
			builder.append(c);
		System.out.println(builder.toString());
	}
	
	private static boolean contains (List<Character> order, List<Character> required) {
		int containsCount = 0;
		for (char c : required) {
			if (order.contains(c))
				containsCount++;
		}
		return containsCount == required.size();
	}
}

class Pair {
	List<Character> required;
	char unlocked;
	
	public Pair (char unlocked, char... required) {
		this.required = new ArrayList<>();
		this.unlocked = unlocked;
		for (char c : required)
			this.required.add(c);
	}
	
	void add (List<Character> c) {
		required.addAll(c);
	}
	
	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pair pair = (Pair) o;
		return unlocked == pair.unlocked;
	}
	
	@Override
	public int hashCode () {
		return Objects.hash(unlocked);
	}
	
	@Override
	public String toString () {
		return "{" + required + "=" + unlocked + "}";
	}
}