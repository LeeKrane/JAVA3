package labors.labor03;

/**
 * @author LeeKrane
 */

public class Bottle<T extends Drink> {
	private T content;
	
	private boolean isEmpty () {
		return content == null;
	}
	
	private void fill (T content) {
		this.content = content;
	}
	
	@SuppressWarnings("UnusedReturnValue")
	private T empty () {
		T old = content;
		content = null;
		return old;
	}
	
	public static void main (String[] args) {
		Bottle<Beer> beer = new Bottle<>();
		beer.fill(new Beer("Zwettler"));
		System.out.println(beer);
		beer.empty();
		System.out.println(beer);
		
		Bottle<Wine> wine = new Bottle<>();
		wine.fill((new WhiteWine("Kamptal")));
		System.out.println(wine);
		wine.empty();
		System.out.println(wine);
		wine.fill(new RedWine("Carnuntum"));
		System.out.println(wine);
	}
	
	@Override
	public String toString () {
		return "Bottle{content -> " + (isEmpty() ? "None" : content) + '}';
	}
}

abstract class Drink {
}

@SuppressWarnings({"SameParameterValue", "unused", "CanBeFinal"})
class Beer extends Drink {
	private String brewery;
	
	Beer (String brewery) {
		this.brewery = brewery;
	}
	
	public String getBrewery () {
		return brewery;
	}
	
	@Override
	public String toString () {
		return "Beer{brewery -> '" + brewery + "'}";
	}
}

@SuppressWarnings({"unused", "CanBeFinal"})
abstract class Wine extends Drink {
	private String region;
	
	Wine (String region) {
		this.region = region;
	}
	
	public String getRegion () {
		return region;
	}
	
	@Override
	public String toString () {
		return (this instanceof WhiteWine ? "WhiteWine{" : "RedWine{") + "region -> '" + region + "'}";
	}
}

@SuppressWarnings("SameParameterValue")
class WhiteWine extends Wine {
	WhiteWine (String region) {
		super(region);
	}
}

@SuppressWarnings("SameParameterValue")
class RedWine extends Wine {
	RedWine (String region) {
		super(region);
	}
}
