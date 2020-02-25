package labors.labor14;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

/**
 * @author LeeKrane
 */

public class InterProcessCommunication {
	public static void main (String[] args) {
		Process process = null;
		try {
			process = Runtime.getRuntime().exec("res/labors/labor14/NumberGuessingGame.exe");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			PrintStream printer = new PrintStream(process.getOutputStream());
			int min = 1, max = 1000, current = 500, counter = 1;
			String line;
			
			printer.println(current);
			printer.flush();
			reader.readLine();
			line = reader.readLine();
			
			while (!"correct".equals(line)) {
				counter++;
				if ("too low".equals(line))
					min = current + 1;
				else
					max = current - 1;
				current = (min + max) / 2;
				printer.println(current);
				printer.flush();
				line = reader.readLine();
			}
			
			System.out.println("The number is: " + current + "\nIt was found after " + counter + " guesses");
			reader.close();
			printer.close();
		}
		catch(Exception e) {
			System.err.println("Error: " +e);
		}
		finally {
			if(process != null)
				process.destroy();
		}
	}
}
