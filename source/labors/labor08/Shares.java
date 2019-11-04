package labors.labor08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Shares
{
	public static void main (String[] args)
	{
		try
		{
			var sharesMap = readFromCSV("resources/labors/labor08/stocks.csv");
			var boughtSharesMap = new HashMap<String, Integer>();
			int money = 100000;
			
			// insert code here
		}
		catch (IOException e) { System.out.println(e.getMessage()); }
	}
	
	private static Map<Integer, List<NamePriceVector2>> readFromCSV (String filePath) throws IOException
	{
		var sharesMap = new HashMap<Integer, List<NamePriceVector2>>();
		BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
		String line;
		String[] split, usedSplit = new String[3];
		
		while (br.ready())
		{
			line = br.readLine();
			if (line.matches("(.+),(\\d),(\\d+)"))
			{
				split = line.split(",");
				
				if (split.length == 4)
				{
					usedSplit[0] = split[0] + ',' + split[1];
					usedSplit[1] = split[2];
					usedSplit[2] = split[3];
				}
				else
					usedSplit = split;
				
				NamePriceVector2 rpv = new NamePriceVector2(usedSplit[0], Integer.parseInt(usedSplit[2]));
				if (sharesMap.containsKey(Integer.parseInt(usedSplit[1])))
				{
					var list = sharesMap.get(Integer.parseInt(usedSplit[1]));
					list.add(rpv);
					sharesMap.put(Integer.parseInt(usedSplit[1]), list);
				}
				else
				{
					var list = new ArrayList<NamePriceVector2>();
					list.add(rpv);
					sharesMap.put(Integer.parseInt(usedSplit[1]), list);
				}
			}
		}
		return sharesMap;
	}
}

class NamePriceVector2
{
	private String name;
	private int price;
	
	NamePriceVector2 (String n, int p)
	{
		name = n;
		price = p;
	}
	
	@Override
	public String toString ()
	{
		return "NPV2{" +
				"name='" + name + '\'' +
				", price=" + price +
				'}';
	}
	
	public String getName () { return name; }
	public int getPrice () { return price; }
}