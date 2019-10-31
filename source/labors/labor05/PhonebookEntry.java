package labors.labor05;

import java.util.*;

/**
 * @author LeeKrane
 */

public class PhonebookEntry implements Comparable<PhonebookEntry>
{
	private String number;
	private String name;
	
	PhonebookEntry (String number, String name)
	{
		if (!number.matches("^\\+[1-9]\\d+") && !number.matches("^[0]\\d+"))
			throw new IllegalArgumentException("Falsche Telefonnummereingabe");
		
		this.number = number;
		this.name = name;
	}
	
	@Override
	public boolean equals (Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		PhonebookEntry that = (PhonebookEntry) o;
		return number.equals(that.number);
	}
	
	@Override
	public int hashCode ()
	{
		return Objects.hash(number);
	}
	
	@Override
	public int compareTo (PhonebookEntry other)
	{
		return Comparator.comparing(PhonebookEntry::getNumber).thenComparing(PhonebookEntry::getName).compare(this, other);
	}
	
	@Override
	public String toString ()
	{
		return "PhonebookEntry{" +
				"number='" + number + '\'' +
				", name='" + name + '\'' +
				'}';
	}
	
	String getNumber ()
	{
		return number;
	}
	
	String getName ()
	{
		return name;
	}
}
