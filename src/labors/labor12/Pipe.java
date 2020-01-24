package labors.labor12;

import java.io.*;

/**
 * @author LeeKrane
 */

public class Pipe {
	/* instructions:
	build project,
	in command prompt:
	cd <project-path>\out\production\JAVA3
	java labors.labor12.Pipe labors\labor12\pipeOutput.txt
	write text,
	press ctrl+z,
	press ctrl+c.
	 */
	public static void main (String[] args) {
		if (args.length != 1) {
			System.err.println("Error: wrong input.\nArguments: <output filename>");
			System.exit(1);
		}
		try {
			PipedOutputStream pipedOutputStream = new PipedOutputStream();
			PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);
			FileOutputStream fileOutputStream = new FileOutputStream(args[0]);
			copySingleByte(System.in, pipedOutputStream);
			copyBuffer(pipedInputStream, fileOutputStream, 4);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	static void copySingleByte (InputStream inputStream, OutputStream outputStream) throws IOException {
		int character;
		while ((character = inputStream.read()) != -1)
			outputStream.write(character);
	}
	
	static void copyBuffer (InputStream inputStream, OutputStream outputStream, int size) throws IOException {
		int n;
		byte[] bytes = new byte[size];
		while ((n = inputStream.read(bytes)) != -1)
			outputStream.write(bytes, 0, n);
	}
}