package labors.bonus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @author LeeKrane
 */

public class Steps {
	public static void main (String[] args) {
		Map<Character, Set<Character>> stepRequirements = new HashMap<>();
		Set<Character> acceptedSteps = new TreeSet<>();
		List<Character> orderedSteps = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader("res/labors/bonus/steps.txt"))) {
			getRequirements(stepRequirements, reader);
			findFirstAcceptedSteps(stepRequirements, acceptedSteps);
			System.out.println("REQUIREMENTS: " + stepRequirements);
			
			while (!stepRequirements.isEmpty()) {
				checkAcceptance(stepRequirements, acceptedSteps, orderedSteps);
				System.out.println("ACCEPTED: " + acceptedSteps);
				addStep(acceptedSteps, orderedSteps);
				System.out.println("ORDERED: " + orderedSteps);
			}
			finalOrderOutput(orderedSteps);
		} catch (IOException e) {
			System.err.println(e + ": " + e.getMessage());
		}
	}
	
	private static void addStep (Set<Character> acceptedSteps, List<Character> orderedSteps) {
		for (char c : acceptedSteps) {
			orderedSteps.add(c);
			acceptedSteps.remove(c);
			break;
		}
	}
	
	private static void finalOrderOutput (List<Character> orderedSteps) {
		StringBuilder builder = new StringBuilder();
		for (char c : orderedSteps)
			builder.append(c);
		System.out.println("FINAL ORDER: " + builder.toString());
	}
	
	private static void findFirstAcceptedSteps (Map<Character, Set<Character>> stepRequirements, Set<Character> acceptedSteps) {
		for (Set<Character> requirements : stepRequirements.values()) {
			for (char requirement : requirements) {
				if (!stepRequirements.containsKey(requirement))
					acceptedSteps.add(requirement);
			}
		}
	}
	
	private static void checkAcceptance (Map<Character, Set<Character>> stepRequirements, Set<Character> acceptedSteps, List<Character> orderedSteps) {
		Set<Character> toRemove = new HashSet<>();
		for (char key : stepRequirements.keySet()) {
			if (orderedSteps.containsAll(stepRequirements.get(key))) {
				acceptedSteps.add(key);
				toRemove.add(key);
			}
		}
		for (char remove : toRemove)
			stepRequirements.remove(remove);
	}
	
	private static void getRequirements (Map<Character, Set<Character>> stepRequirements, BufferedReader reader) throws IOException {
		String line;
		char step;
		char requirement;
		
		while (reader.ready()) {
			line = reader.readLine();
			step = line.charAt(36);
			requirement = line.charAt(5);
			
			if (stepRequirements.containsKey(step))
				stepRequirements.get(step).add(requirement);
			else {
				Set<Character> currentRequirements = new HashSet<>();
				currentRequirements.add(requirement);
				stepRequirements.put(step, currentRequirements);
			}
		}
	}
}