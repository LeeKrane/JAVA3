package labors.labor12;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DeleteLinesTest {
	private static String inputFile = "res/labors/labor12/input.txt";
	private static String outputFile = "res/labors/labor12/output.txt";
	
	@Test
	public void deleteZeroLinesTest () {
		DeleteLines.main(new String[]{"DeleteLines", inputFile, outputFile, "0"});
		try (DataInputStream input = new DataInputStream(new BufferedInputStream(new AsciiInputStream(inputFile))); DataInputStream output = new DataInputStream(new BufferedInputStream(new AsciiInputStream(outputFile)))) {
			assertArrayEquals(input.readAllBytes(), output.readAllBytes());
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test
	public void deleteXLinesTest () {
		DeleteLines.main(new String[]{"DeleteLines", inputFile, outputFile, "3"});
		try (DataInputStream input = new DataInputStream(new BufferedInputStream(new AsciiInputStream(inputFile))); DataInputStream output = new DataInputStream(new BufferedInputStream(new AsciiInputStream(outputFile)))) {
			for (int i = 0; i < 3; i++)
				input.readLine();
			assertArrayEquals(input.readAllBytes(), output.readAllBytes());
		} catch (IOException e) {
			fail();
		}
		DeleteLines.main(new String[]{"DeleteLines", inputFile, outputFile, "17"});
		try (DataInputStream input = new DataInputStream(new BufferedInputStream(new AsciiInputStream(inputFile))); DataInputStream output = new DataInputStream(new BufferedInputStream(new AsciiInputStream(outputFile)))) {
			for (int i = 0; i < 17; i++)
				input.readLine();
			assertArrayEquals(input.readAllBytes(), output.readAllBytes());
		} catch (IOException e) {
			fail();
		}
		DeleteLines.main(new String[]{"DeleteLines", inputFile, outputFile, "42"});
		try (DataInputStream input = new DataInputStream(new BufferedInputStream(new AsciiInputStream(inputFile))); DataInputStream output = new DataInputStream(new BufferedInputStream(new AsciiInputStream(outputFile)))) {
			assertArrayEquals(new byte[]{}, output.readAllBytes());
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test
	public void deleteLineIntervalSameNumberTest () {
		DeleteLines.main(new String[]{"DeleteLines", inputFile, outputFile, "0-0"});
		try (DataInputStream input = new DataInputStream(new BufferedInputStream(new AsciiInputStream(inputFile))); DataInputStream output = new DataInputStream(new BufferedInputStream(new AsciiInputStream(outputFile)))) {
			assertArrayEquals(input.readAllBytes(), output.readAllBytes());
		} catch (IOException e) {
			fail();
		}
		DeleteLines.main(new String[]{"DeleteLines", inputFile, outputFile, "7-7"});
		try (DataInputStream input = new DataInputStream(new BufferedInputStream(new AsciiInputStream(inputFile))); DataInputStream output = new DataInputStream(new BufferedInputStream(new AsciiInputStream(outputFile)))) {
			assertArrayEquals(input.readAllBytes(), output.readAllBytes());
		} catch (IOException e) {
			fail();
		}
		DeleteLines.main(new String[]{"DeleteLines", inputFile, outputFile, "42-42"});
		try (DataInputStream input = new DataInputStream(new BufferedInputStream(new AsciiInputStream(inputFile))); DataInputStream output = new DataInputStream(new BufferedInputStream(new AsciiInputStream(outputFile)))) {
			assertArrayEquals(input.readAllBytes(), output.readAllBytes());
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test
	public void deleteLineIntervalTest () {
		DeleteLines.main(new String[]{"DeleteLines", inputFile, outputFile, "1-7"});
		try (DataInputStream input = new DataInputStream(new BufferedInputStream(new AsciiInputStream(inputFile))); DataInputStream output = new DataInputStream(new BufferedInputStream(new AsciiInputStream(outputFile)))) {
			assertEquals(input.readLine(), output.readLine());
			for (int i = 1; i < 7; i++)
				input.readLine();
			assertArrayEquals(input.readAllBytes(), output.readAllBytes());
		} catch (IOException e) {
			fail();
		}
		DeleteLines.main(new String[]{"DeleteLines", inputFile, outputFile, "7-42"});
		try (DataInputStream input = new DataInputStream(new BufferedInputStream(new AsciiInputStream(inputFile))); DataInputStream output = new DataInputStream(new BufferedInputStream(new AsciiInputStream(outputFile)))) {
			for (int i = 0; i < 7; i++)
				assertEquals(input.readLine(), output.readLine());
			assertArrayEquals(new byte[]{}, output.readAllBytes());
		} catch (IOException e) {
			fail();
		}
		DeleteLines.main(new String[]{"DeleteLines", inputFile, outputFile, "0-42"});
		try (DataInputStream input = new DataInputStream(new BufferedInputStream(new AsciiInputStream(inputFile))); DataInputStream output = new DataInputStream(new BufferedInputStream(new AsciiInputStream(outputFile)))) {
			assertArrayEquals(new byte[]{}, output.readAllBytes());
		} catch (IOException e) {
			fail();
		}
		DeleteLines.main(new String[]{"DeleteLines", inputFile, outputFile, "7-3"});
		try (DataInputStream input = new DataInputStream(new BufferedInputStream(new AsciiInputStream(inputFile))); DataInputStream output = new DataInputStream(new BufferedInputStream(new AsciiInputStream(outputFile)))) {
			assertArrayEquals(input.readAllBytes(), output.readAllBytes());
		} catch (IOException e) {
			fail();
		}
	}
}