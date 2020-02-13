package labors.labor04;

@SuppressWarnings("unused")
public interface IMap<K, V> {
	/**
	 * Fuegt ein neues Schluessel-Wertepaar dieser Map hinzu.
	 * Sollte der Schluessel schon existieren, wir der Wert ersetzt
	 * und der alte Wert wird retourniert.
	 * Schluessel mit wert null werden abgelehnt.
	 *
	 * @return Den alten Wert bzw. null, wenn der Schluessel
	 * neu oder key gleich null ist.
	 */
	V put (K key, V value);
	
	/**
	 * Liefert den zum Schluessel key gehoerigen Wert bzw. null,
	 * wenn der Schluessel key nicht existiert.
	 *
	 * @return Den zu key gehoerigen Wert bzw. null
	 */
	V get (K key);
	
	/**
	 * Liefert die Anzahl der Schluessel-Werte-Paare in dieser Map.
	 *
	 * @return Anzahl der Eintraege in dieser Map
	 */
	int size ();
}