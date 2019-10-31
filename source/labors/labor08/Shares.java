package labors.labor08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Shares
{
	public static void main (String[] args)
	{
		try
		{
			var list = readFromCSV("resources/labors/labor08/stocks.csv");
			for (int i = 0; i < list.size(); i++)
			{
				System.out.println(list.get(i));
			}
		}
		catch (IOException e) { System.out.println(e.getMessage()); }
	}
	
	private static List<GenericVector3<Integer, String, Integer>> readFromCSV (String filepath) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(new File(filepath)));
		String line;
		String[] split;
		
		return null;
	}
}

class GenericVector3<A, B, C>
{
	private A a;
	private B b;
	private C c;
	
	public GenericVector3 (A a, B b, C c)
	{
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	@Override
	public String toString ()
	{
		return "GenericVector3{" +
				"a=" + a +
				", b=" + b +
				", c=" + c +
				'}';
	}
}