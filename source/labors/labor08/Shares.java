package labors.labor08;

import java.io.BufferedReader; // for non-fancy variant
import java.io.File; // for non-fancy variant
import java.io.FileReader; // for non-fancy variant
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

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
			Queue<Shares> sharesQueue = readFromCSV("resources/labors/labor08/stocks.csv");
			System.out.println(sharesQueue);
			Map<Shares, Integer> boughtSharesMap = new TreeMap<>();
			int capital = 100000, currentCapital = capital, i;
			
			while (currentCapital > 0 && !sharesQueue.isEmpty())
			{
				Shares share = sharesQueue.poll();
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
		rating = Integer.parseInt(split[split.length - 2]);
		price = Integer.parseInt(split[split.length - 1]);
		priceRatingRatio = (double) price / (double) rating;
		companyName = split[0];
		if (split.length == 4)
			companyName += ',' + split[1];
		if (companyName.contains("\""))
			companyName = companyName.substring(1, companyName.length() - 2);
	}
	
	private static Queue<Shares> readFromCSV (String filePath) throws IOException
	{
		return Files.lines(Paths.get(filePath))
				.filter(line -> line.matches("(.+),(\\d),(\\d+)"))
				.map(Shares::new)
				.collect(Collectors.toCollection(PriorityQueue::new));
	}
	
	// non-fancy variant:
	private static Queue<Shares> readFromCSV2 (String filePath) throws IOException
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
	private int getPrice () { return price; }
	
	@Override
	public int compareTo (Shares other)
	{
		return Comparator.comparing(Shares::getPriceRatingRatio).thenComparing(Shares::getPrice).compare(this, other);
	}
	
	@Override
	public String toString ()
	{
		return companyName + " -> Rating: " + rating + ", Price: " + price + ", Ratio: " + priceRatingRatio;
	}
}