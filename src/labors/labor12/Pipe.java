package labors.labor12;

import java.io.*;

public class Pipe {
	public static void main (String[] args) {
		try (PipedOutputStream pos = new PipedOutputStream()) {
			CopyLowLevel.copySingleByte(System.in, pos);
			try (PipedInputStream pis = new PipedInputStream(pos); FileOutputStream fos = new FileOutputStream("res/labors/labor12/pipeOutput")) {
				CopyLowLevel.copyBuffer(pis, fos, 4);
			}
		} catch (IOException e) {
			System.err.println("This is not done yet.");
		}
	}
}

class CopyLowLevel {
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