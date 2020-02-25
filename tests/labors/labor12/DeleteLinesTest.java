package labors.labor12;

import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author LeeKrane
 */

@SuppressWarnings("CanBeFinal")
public class DeleteLinesTest {
	private static String inputFile = "res/labors/labor12/org.txt";
	private static String outputFile = "res/labors/labor12/output.txt";
	
	@Test
	void deleteLines_noArguments_deleteZeroLines () throws IOException {
		DeleteLines.main(new String[] {inputFile, outputFile});
		try (AsciiInputStream input = new AsciiInputStream(inputFile); AsciiInputStream output = new AsciiInputStream(outputFile)) {
			assertArrayEquals(input.readAllBytes(), output.readAllBytes());
		}
	}
	
	@Test
	void deleteLines_oneArgument_deleteSingleLine1 () throws IOException {
		DeleteLines.main(new String[] {inputFile, outputFile, "0"});
		try (AsciiInputStream input = new AsciiInputStream(inputFile); AsciiInputStream output = new AsciiInputStream(outputFile)) {
			input.skipLine();
			assertArrayEquals(input.readAllBytes(), output.readAllBytes());
		}
	}
	
	@Test
	void deleteLines_oneArgument_deleteSingleLine2 () throws IOException {
		DeleteLines.main(new String[] {inputFile, outputFile, "7"});
		try (AsciiInputStream input = new AsciiInputStream(inputFile); AsciiInputStream output = new AsciiInputStream(outputFile)) {
			for (int i = 0; i < 7; i++)
				assertEquals(input.readLine(), output.readLine());
			input.skipLine();
			assertArrayEquals(input.readAllBytes(), output.readAllBytes());
		}
	}
	
	@Test
	void deleteLines_oneArgument_deleteSingleLine3 () throws IOException {
		DeleteLines.main(new String[] {inputFile, outputFile, "14"});
		try (AsciiInputStream input = new AsciiInputStream(inputFile); AsciiInputStream output = new AsciiInputStream(outputFile)) {
			for (int i = 0; i < 14; i++)
				assertEquals(input.readLine(), output.readLine());
			input.skipLine();
			assertArrayEquals(input.readAllBytes(), output.readAllBytes());
		}
	}
	
	@Test
	void deleteLines_oneArgument_deleteSingleLine4 () throws IOException {
		DeleteLines.main(new String[] {inputFile, outputFile, "21"});
		try (AsciiInputStream input = new AsciiInputStream(inputFile); AsciiInputStream output = new AsciiInputStream(outputFile)) {
			assertArrayEquals(input.readAllBytes(), output.readAllBytes());
		}
	}
	
	@Test
	void deleteLines_interval_deleteLinesInInterval1 () throws IOException {
		DeleteLines.main(new String[] {inputFile, outputFile, "0-3"});
		try (AsciiInputStream input = new AsciiInputStream(inputFile); AsciiInputStream output = new AsciiInputStream(outputFile)) {
			input.skipLines(4);
			assertArrayEquals(input.readAllBytes(), output.readAllBytes());
		}
	}
	
	@Test
	void deleteLines_interval_deleteLinesInInterval2 () throws IOException {
		DeleteLines.main(new String[] {inputFile, outputFile, "7-10"});
		try (AsciiInputStream input = new AsciiInputStream(inputFile); AsciiInputStream output = new AsciiInputStream(outputFile)) {
			for (int i = 0; i < 7; i++)
				assertEquals(input.readLine(), output.readLine());
			input.skipLines(4);
			assertArrayEquals(input.readAllBytes(), output.readAllBytes());
		}
	}
	
	@Test
	void deleteLines_interval_deleteLinesInInterval3 () throws IOException {
		DeleteLines.main(new String[] {inputFile, outputFile, "14-17"});
		try (AsciiInputStream input = new AsciiInputStream(inputFile); AsciiInputStream output = new AsciiInputStream(outputFile)) {
			for (int i = 0; i < 14; i++)
				assertEquals(input.readLine(), output.readLine());
			input.skipLines(4);
			assertArrayEquals(input.readAllBytes(), output.readAllBytes());
		}
	}
	
	@Test
	void deleteLines_interval_deleteLinesInInterval4 () throws IOException {
		DeleteLines.main(new String[] {inputFile, outputFile, "21-24"});
		try (AsciiInputStream input = new AsciiInputStream(inputFile); AsciiInputStream output = new AsciiInputStream(outputFile)) {
			assertArrayEquals(input.readAllBytes(), output.readAllBytes());
		}
	}
	
	@Test
	void deleteLines_multipleArguments_deleteAllLinesOfArguments1 () throws IOException {
		DeleteLines.main(new String[] {inputFile, outputFile, "0", "5", "10-17"});
		try (AsciiInputStream input = new AsciiInputStream(inputFile); AsciiInputStream output = new AsciiInputStream(outputFile)) {
			input.skipLine();
			for (int i = 1; i < 5; i++)
				assertEquals(input.readLine(), output.readLine());
			input.skipLine();
			for (int i = 6; i < 10; i++)
				assertEquals(input.readLine(), output.readLine());
			input.skipLines(8);
			assertArrayEquals(input.readAllBytes(), output.readAllBytes());
		}
	}
	
	@Test
	void deleteLines_multipleArguments_deleteAllLinesOfArguments2 () throws IOException {
		DeleteLines.main(new String[] {inputFile, outputFile, "7", "17-33"});
		try (AsciiInputStream input = new AsciiInputStream(inputFile); AsciiInputStream output = new AsciiInputStream(outputFile)) {
			for (int i = 0; i < 7; i++)
				assertEquals(input.readLine(), output.readLine());
			input.skipLine();
			for (int i = 8; i < 17; i++)
				assertEquals(input.readLine(), output.readLine());
			assertArrayEquals(new byte[]{}, output.readAllBytes());
		}
	}
	
	@Test
	void deleteLines_multipleArguments_deleteAllLinesOfArguments3 () throws IOException {
		DeleteLines.main(new String[] {inputFile, outputFile, "0", "0", "0", "0"});
		try (AsciiInputStream input = new AsciiInputStream(inputFile); AsciiInputStream output = new AsciiInputStream(outputFile)) {
			input.skipLine();
			assertArrayEquals(input.readAllBytes(), output.readAllBytes());
		}
	}
	
	@Test
	void deleteLines_multipleArguments_deleteAllLinesOfArguments4 () throws IOException {
		DeleteLines.main(new String[] {inputFile, outputFile, "7-4", "19-11", "10"});
		try (AsciiInputStream input = new AsciiInputStream(inputFile); AsciiInputStream output = new AsciiInputStream(outputFile)) {
			for (int i = 0; i < 10; i++)
				assertEquals(input.readLine(), output.readLine());
			input.skipLine();
			assertArrayEquals(input.readAllBytes(), output.readAllBytes());
		}
	}
}