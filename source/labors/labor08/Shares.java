package labors.labor08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.TreeMap;

/**
 * @author LeeKrane
 */

public class Shares implements Comparable<Shares>
{
	private String companyName;
	private double priceRatingRatio;
	private int rating;
	private int price;
	
	public static void main (String[] args)
	{
		try
		{
			var sharesQueue = readFromCSV("resources/labors/labor08/stocks.csv");
			var boughtSharesMap = new TreeMap<Shares, Integer>();
			int capital = 100000, currentCapital = capital, i;
			
			while (currentCapital > 0 && !sharesQueue.isEmpty())
			{
				var share = sharesQueue.poll();
				i = 1;
				
				while (share.price * i <= capital / 5 && currentCapital - share.price > 0)
				{
					i++;
					currentCapital -= share.price;
				}
				
				if (i > 1)
					boughtSharesMap.put(share, i-1);
			}
			boughtSharesMap.forEach((s, c) -> System.out.println(s.companyName + ": " + c + " Shares"));
			System.out.println("Leftover Money: " + currentCapital);
		}
		catch (IOException e) { System.err.println(e.getMessage()); }
	}
	
	private Shares (String input)
	{
		String[] split = input.split(",");
		rating = Integer.parseInt(split[split.length-2]);
		price = Integer.parseInt(split[split.length-1]);
		priceRatingRatio = (double) price / (double) rating;
		companyName = split[0];
		if (split.length == 4)
			companyName += ',' + split[1];
		if (companyName.contains("\""))
			companyName = companyName.substring(1, companyName.length()-2);
	}
	
	private static Queue<Shares> readFromCSV (String filePath) throws IOException
	{
		var sharesQueue = new PriorityQueue<Shares>();
		BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
		String line;
		
		while (br.ready())
		{
			line = br.readLine();
			if (line.matches("(.+),(\\d),(\\d+)"))
				sharesQueue.add(new Shares(line));
		}
		return sharesQueue;
	}
	
	private double getPriceRatingRatio () { return priceRatingRatio; }
	private int getRating () { return rating; }
	private int getPrice () { return price; }
	
	@Override
	public int compareTo (Shares other)
	{
		//return Comparator.comparing(Shares::getRating).reversed().thenComparing(Shares::getPriceRatingRatio).thenComparing(Shares::getPrice).compare(this, other);
		return Comparator.comparing(Shares::getPriceRatingRatio).thenComparing(Shares::getPrice).compare(this, other);
	}
	
	@Override
	public String toString ()
	{
		return companyName + " -> Rating: " + rating + ", Price: " + price + ", Ratio: " + priceRatingRatio;
	}
}