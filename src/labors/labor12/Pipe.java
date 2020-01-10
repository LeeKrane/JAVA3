package labors.labor12;

import java.io.*;

/**
 * @author LeeKrane
 */

public class Pipe {
	/*
	build project.
	in command prompt:
	cd <project-path>\out\production\JAVA3
	java labors.labor12.Pipe
	write text.
	press ctrl+z
	press ctrl+c
	 */
	
	public static void main (String[] args) {
		try {
			PipedOutputStream pipedOutputStream = new PipedOutputStream();
			PipedInputStream pipedInputStream = new PipedInputStream(pipedOutputStream);
			FileOutputStream fileOutputStream = new FileOutputStream("out/production/JAVA3/labors/labor12/pipeOutput.txt");
			copySingleByte(System.in, pipedOutputStream);
			copyBuffer(pipedInputStream, fileOutputStream, 4);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	static void copySingleByte (InputStream inputStream, OutputStream outputStream) throws IOException {
		int character;
		while ((character = inputStream.read()) != -1) {
			outputStream.write(character);
		}
	}
	
	static void copyBuffer (InputStream inputStream, OutputStream outputStream, int size) throws IOException {
		int n;
		byte[] bytes = new byte[size];
		while ((n = inputStream.read(bytes)) != -1)
			outputStream.write(bytes, 0, n);
	}
}