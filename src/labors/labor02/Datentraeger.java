package labors.labor02;

/**
 * @author LeeKrane
 */

public class Datentraeger {
	private String titel;
	private double preis;
	private int megaByteGroesse;
	private static int gesamtAnzahl = 0;
	
	public static void main (String[] args) {
		Datentraeger cd = new CD("Sam der Rapper", 5.99, 659, true);
		Datentraeger dvd = new DVD("Geheime Zusammenfassung der NOST-Konformen GGP Tests", 49.99, 25498, false, '+');
		
		System.out.println(cd + "\n" + dvd);
		System.out.println("Instanzen: " + gesamtAnzahl);
		
		dvd.setTitel("Why you should buy - Remnant: From the Ashes!");
		System.out.println(dvd);
		
		try {
			cd.setPreis(-420.69);
		} catch (WertNegativException wne) {
			System.err.println(wne.getMessage());
		}
	}
	
	Datentraeger (String titel, double preis, int megaByteGroesse) {
		if (preis < 0 || megaByteGroesse < 0)
			throw new WertNegativException("Preis bzw. MegaByteGröße darf nicht negativ sein!");
		setTitel(titel);
		setPreis(preis);
		this.megaByteGroesse = megaByteGroesse;
		gesamtAnzahl++;
	}
	
	private void setPreis (double preis) {
		if (preis < 0) throw new WertNegativException("Preis darf nicht negativ sein!");
		this.preis = preis;
	}
	
	private void setTitel (String titel) {
		this.titel = titel;
	}
	
	String getTitel () {
		return titel;
	}
	
	double getPreis () {
		return preis;
	}
	
	int getMegaByteGroesse () {
		return megaByteGroesse;
	}
}

@SuppressWarnings({"SameParameterValue", "CanBeFinal"})
class CD extends Datentraeger {
	private boolean readOnly;
	
	CD (String titel, double preis, int megaByteGroesse, boolean readOnly) {
		super(titel, preis, megaByteGroesse);
		this.readOnly = readOnly;
	}
	
	@Override
	public String toString () {
		return "CD: " + super.getTitel() + ", Preis: " + super.getPreis() + ", MegaByteGröße: " + super.getMegaByteGroesse() + (this.readOnly ? ", ReadOnly" : "");
	}
}

@SuppressWarnings({"SameParameterValue", "CanBeFinal"})
class DVD extends Datentraeger {
	private boolean readOnly;
	private char plusOderMinus;
	
	DVD (String titel, double preis, int megaByteGroesse, boolean readOnly, char plusOderMinus) {
		super(titel, preis, megaByteGroesse);
		this.readOnly = readOnly;
		this.plusOderMinus = plusOderMinus;
	}
	
	@Override
	public String toString () {
		return "DVD" + this.plusOderMinus + ": " + super.getTitel() + ", Preis: " + super.getPreis() + ", MegaByteGröße: " + super.getMegaByteGroesse() + (this.readOnly ? ", ReadOnly" : "");
	}
}

class WertNegativException extends RuntimeException {
	WertNegativException (String message) {
		super(message);
	}
}