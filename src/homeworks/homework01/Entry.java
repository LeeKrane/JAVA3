package homeworks.homework01;

import java.util.Comparator;
import java.util.Objects;
/**
 * TODO: make all tests work. NOTE: I do not have to do this homework yet, but I started anyway.
 */

/**
 * Klasse: Entry<br>
 * <br>
 * Jeder Eintrag besteht aus einem Schluessel und einem dazugehoerigen Wert.<br>
 * Die Klasse soll so entwickelt werden, dass als Schluessel und Wert beliebige
 * Datentypen eingesetzt werden koennen.<br>
 * Es ist bei allen Methoden sicherzustellen, dass sie typsicher sind. Ein
 * fehlerhafter Aufruf soll bereits durch den Java-Compiler erkannt werden
 * (Stichwort Generics).<br>
 * <br>
 * Zwei Eintraege sollen zudem vergleichbar sein, d.h. die Klasse muss das
 * Interface Comparable implementieren. Fuer den Vergleich ist jedoch nur der
 * Schluessel relevant, nur ueber ihn wird entschieden, ob ein Eintrag kleiner,
 * groesser oder gleich einem anderen Eintrag ist. Es ist somit sicherzustellen,
 * das der Typ des Schluessels ebenfalls vergleichbar, also Comparable, ist.
 */

public class Entry<K, V> implements Comparable<Entry<K, V>> {
	
	/**
	 * Datenfelder, alle nicht direkt von aussen zugreifbar.<br>
	 * Es werden mindestens Datenfelder fuer Schluessel und Wert benoetigt.
	 */
	private K key;
	private V value;
	
	/**
	 * default-Konstruktor<br />
	 * <br />
	 * Schluessel und Wert sind mit "null" zu initalisieren.
	 */
	Entry () {
		this(null, null);
	}
	
	/**
	 * Konstruktor
	 *
	 * @param key   der Schluessel
	 * @param value der Wert des Schluessels
	 */
	Entry (K key, V value) {
		setKey(key);
		setValue(value);
	}
	
	/**
	 * Methode: getKey
	 *
	 * @return liefert den Schluessel des Eintrags
	 */
	K getKey () {
		return key;
	}
	
	/**
	 * Methode: getValue
	 *
	 * @return liefert den Wert des Eintrags
	 */
	V getValue () {
		return value;
	}
	
	/**
	 * Methode: setKey() <br/>
	 * Setzen des Schluessels
	 *
	 * @param key
	 */
	void setKey (K key) {
		this.key = key;
	}
	
	/**
	 * Methode: setValue<br/>
	 * Setzen des Wertes
	 *
	 * @param value
	 */
	void setValue (V value) {
		this.value = value;
	}
	
	/**
	 * Methode: compareTo()<br>
	 * Vergleicht zwei Eintraege, zwei Eintraege werden nur ueber den Schluessel
	 * vergleichen, der Wert des Eintrags braucht fuer den Vergleich nicht
	 * beruecksichtigt zu werden. Wirft eine NullPointerException, wenn einer der
	 * zu vergeleichenden Schluessel null ist
	 *
	 * @param e der zu vergleichende Eintrag
	 * @return Ergebnis des Vergleiches
	 * @throws NullPointerException
	 * @see java.lang.Comparable<Entry></K,>>.compareTo
	 */
	@Override
	public int compareTo (Entry<K, V> e) throws NullPointerException {
		if (this.getKey() == null || e.getKey() == null)
			throw new NullPointerException();
		
		if (getKey().equals(e.getKey()))
			return 0;
		return Comparator.comparing(entry -> ((Entry<K, V>) entry).getKey().equals(getKey())).compare(this, e);
	}
	
	/**
	 * Methode: equals<br/>
	 * Vergleicht, ob zwei Eintraege gleich sind. Dies ist der Fall, wenn der
	 * Schluessel und der Wert identisch sind.
	 *
	 * @param o
	 * @return true wenn Schluessel und Wert indentisch ist, ansonsten false
	 */
	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Entry<?, ?> entry = (Entry<?, ?>) o;
		return Objects.equals(key, entry.key) &&
				Objects.equals(value, entry.value);
	}
	
	@Override
	public int hashCode () {
		return Objects.hash(key, value);
	}
	
	/**
	 * Methode: toString<br/>
	 * Stringrepraesentation eines Eintrags: Schluessel und Wert sind durch
	 * einen Pfeil "->" (ohne Leerzeichen oder sonstiges) zu trennen, z.B:
	 * schluessel1->wert1
	 *
	 * @return Stringrepraesentation eines Eintrags
	 */
	@Override
	public String toString () {
		return key + "->" + value;
	}
}
