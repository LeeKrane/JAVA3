package labors.labor01;

/**
 * @author LeeKrane
 */

public class DVD
{
	private String titel;
	private double preis;
	private int anzahl;
	private static int gesamtAnzahl;
	
	public static void main(String []args) {
		DVD dvd1 = new DVD("Alles geht!", 10.9, 5);
		DVD dvd2 = new DVD("Country Ballads", 9.5); // 1 Stueck
		DVD dvd3 = new DVD("Best of Johnny Cash", 9.5, 2);
		DVD[] lager = {dvd1, dvd2, dvd3};
		System.out.format("Gesamzahl der DVDs: %d\n", DVD.getGesamtAnzahl());
		System.out.format("Gesamtwert des Lagers: %5.2f\n", DVD.gesamtWert(lager));
		dvd1.verkaufe(2);
		try
		{
			dvd2.verkaufe(3); // wirft eine StueckZahlException
		}
		catch (StueckZahlException sze)
		{
			System.err.println(sze);
		}
		System.out.format("Gesamzahl der DVDs: %d\n", DVD.getGesamtAnzahl());
		System.out.format("Gesamtwert des Lagers: %5.2f\n", DVD.gesamtWert(lager));
	}
	
	private DVD (String titel, double preis, int anzahl)
	{
		if (anzahl < 1)
			throw new StueckZahlException("Lagerbestand kleiner 1.");
		this.titel = titel;
		this.preis = preis;
		this.anzahl = anzahl;
		gesamtAnzahl += anzahl;
	}
	
	private DVD (String titel, double preis)
	{
		this(titel, preis, 1);
	}
	
	private static int getGesamtAnzahl ()
	{
		return DVD.gesamtAnzahl;
	}
	
	private static double gesamtWert (DVD[] liste)
	{
		double gesamtWert = 0.0;
		
		for (int i = 0; i < liste.length; i++)
			gesamtWert += liste[i].preis * liste[i].anzahl;
		
		return gesamtWert;
	}
	
	private void verkaufe (int anzahl)
	{
		if (anzahl > this.anzahl)
			throw new StueckZahlException("Fehler: Zu wenig Stück auf Lager.");
		else if (anzahl < 1)
			throw new StueckZahlException("Fehler: Kann nicht weniger als 1 Stück verkaufen.");
		
		this.anzahl -= anzahl;
		gesamtAnzahl -= anzahl;
	}
}

class StueckZahlException extends RuntimeException
{
	StueckZahlException (String msg)
	{
		super(msg);
	}
	
	/*
	public StueckZahlException ()
	{
		super();
	}
	*/
}