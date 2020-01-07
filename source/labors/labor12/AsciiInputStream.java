package labors.labor12;

import java.io.*;

/**
 * @author LeeKrane
 */

public class AsciiInputStream extends FileInputStream {
	public AsciiInputStream (String name) throws FileNotFoundException {
		super(name);
	}
	
	public AsciiInputStream (File file) throws FileNotFoundException {
		super(file);
	}
	
	public String readLine () throws IOException {
		StringBuilder builder = new StringBuilder();
		char c;
		while ((c = (char) read()) != -1 && c != '\n')
			builder.append(c);
		return builder.toString();
	}
	
	public void skipLines (long toSkip) throws IOException {
		for (int i = 0; i < toSkip; i++)
			readLine();
	}
}
