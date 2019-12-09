package homeworks.homework01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Klasse: Dictionary<br/>
 * <br/>
 * Die Klasse Dictionary verwaltet eine Liste von Eintraegen (Instanzen der
 * Klasse: Entry)<br/>
 * <br/>
 * Welche konkrete Implementierung fuer die Liste eingesetzt wird, ist durch die
 * Aufgabenstellung NICHT vorgeschrieben.<br/>
 * Es muss sich lediglich um eine Implementierung des Interfaces List&lt;E&gt;
 * aus dem Paket "java.util" sein.<br>
 * <br>
 * Neben der Liste wird mindestens ein weiteres Datenfeld benoetigt, dass als
 * internes Flag dienen soll und anzeigt, ob ein Schluessel mehrfach im
 * Dictionary vorkommen darf.<br>
 * <br>
 * Alle Datenfelder duerfen von aussen nicht direkt zugreifbar sein und koennen
 * nur ueber get-/set-Methoden veraendert werden.
 */
public class Dictionary<K, V> {
	/**
	 * Datenfelder, alle von aussen nicht direkt zugreifbar
	 */
	private List<Entry<K, V>> entries = new ArrayList<>();
	private final boolean allowMultipleEntries;
	
	/**
	 * Default-Konstruktor<br>
	 * <br>
	 * Wird dieser verwendet, darf nur ein Eintrag zu einem Schluessel vorhanden
	 * sein
	 */
	Dictionary () {
		allowMultipleEntries = false;
	}
	
	/**
	 * Konstruktor
	 *
	 * @param allowMultipleEntries true wenn mehrere Eintraege zulaessig, ansonsten false
	 */
	Dictionary (boolean allowMultipleEntries) {
		this.allowMultipleEntries = allowMultipleEntries;
	}
	
	/**
	 * Methode: getEntries<br/>
	 * Liefert alle Eintraege des Dictionary, sind keine Eintraege vorhanden, so
	 * wird eine leere Liste geliefert
	 *
	 * @return Liste der Eintraege
	 */
	List<Entry<K, V>> getEntries () {
		return entries;
	}
	
	/**
	 * Methode: add<br/>
	 * Hinzufuegen eines Eintrags mit Schluessel und Wert
	 *
	 * @param key   der Schluessel des neuen Eintrags
	 * @param value der Wert des neuen Eintrags
	 * @return true bei Erfolg, false wenn der Eintrag nicht eingefuegt
	 * werden konnte
	 */
	boolean add (K key, V value) {
		return add(new Entry<>(key, value));
	}
	
	/**
	 * Methode: add<br>
	 * Hinzufuegen eines Eintrags.
	 *
	 * @param entry Hinzuzufuegender Eintrag
	 * @return true bei Erfolg, false wenn der Eintrag nicht eingefuegt
	 * werden konnte
	 */
	boolean add (Entry<K, V> entry) {
		if (allowMultipleEntries || !isIn(entry.getKey())) {
			entries.add(entry);
			return true;
		}
		return false;
	}
	
	/**
	 * Methode: del<br/>
	 * Loeschen eines Eintrags. Sind mehrere Eintraege mit demselben Schluessel
	 * vorhanden, wird nur das erste Vorkommen (zeitliche Reihenfolge des
	 * Einfuegens) geloescht.
	 *
	 * @param key der Schluessel, zudem ein Eintrag geloescht werden sollen
	 * @return der geloeschte Eintrag, null wenn nichs geloescht wurde
	 */
	Entry<K, V> del (K key) {
		for (Entry<K, V> entry : entries) {
			if (entry.getKey() == key) {
				entries.remove(entry);
				return entry;
			}
		}
		return null;
	}
	
	/**
	 * Methode: delAll<br/>
	 * Loeschen saemtlicher Eintraege zu einem Schluessel
	 *
	 * @param key der Schluessel, zudem alle Eintraege geloescht werden sollen
	 * @return alle geloeschten Eintraege als Liste
	 */
	List<Entry<K, V>> delAll (K key) {
		List<Entry<K, V>> deletedEntries = new ArrayList<>();
		while (isIn(key))
			deletedEntries.add(del(key));
		return deletedEntries;
	}
	
	/**
	 * Methode: isIn<br>
	 * Prueft, ob der Schluessel vorhanden ist
	 *
	 * @param key
	 * @return true wenn ein Eintrag mit dem Schluessel enthalten ist, ansonsten
	 * false
	 */
	boolean isIn (K key) {
		for (Entry<K, V> entry : entries) {
			if (entry.getKey() == key)
				return true;
		}
		return false;
	}
	
	/**
	 * Methode: lookupFirst<br>
	 * Sucht das erste Vorkommen mit passendem Schluessel und gibt den Wert
	 * zurueck<br>
	 * Ist der Schluessel nicht vorhanden, soll null zurueckgeliefert werden
	 *
	 * @param key
	 * @return Erster passender Wert zu uebergebenem Schluessel
	 */
	V lookupFirst (K key) {
		if (isIn(key)) {
			for (Entry<K, V> entry : entries) {
				if (entry.getKey() == key)
					return entry.getValue();
			}
		}
		return null;
	}
	
	/**
	 * Methode: lookupLast<br>
	 * Sucht das letzte Vorkommen mit passendem Schluessel und gibt den Wert
	 * zurueck<br>
	 * Ist der Schluessel nicht vorhanden, soll null zurueckgeliefert werden
	 *
	 * @param key
	 * @return Letzter passender Wert zu uebergebenem Schluessel
	 */
	V lookupLast (K key) {
		if (isIn(key)) {
			List<Entry<K, V>> reversedEntries = new ArrayList<>(entries);
			Collections.reverse(reversedEntries);
			for (Entry<K, V> entry : reversedEntries) {
				if (entry.getKey() == key)
					return entry.getValue();
			}
		}
		return null;
	}
	
	/**
	 * Methode: lookupAll<br>
	 * Sucht alle Eintraege mit passendem Schluessel und gibt die vollstaendigen
	 * Eintraege zurueck.<br>
	 * Die Eintraege sollen in alphabetischer Reihenfolge aufsteigend sortiert
	 * sein. Ist kein Schluessel vorhanden, soll eine leere Liste
	 * zurueckgeliefert werden. Zum Sortieren soll die statische Methode: sort
	 * aus Klasse: java.util.Collections eingesetzt werden.
	 *
	 * @param key die zu suchenden Schluessel, es koennen beliebig viele (auch
	 *            0) als Parameter angegeben werden.<br>
	 *            Hinweis: Dieses ist mit einer speziellen Syntax moeglich, die
	 *            ab Java Version 1.5 vorhanden ist.<br>
	 *            Folgende Aufrufe sind beispielsweise gueltig:<br>
	 *            <ul>
	 *            <li>lookupAll()</li>
	 *            <li>lookupAll(key1)</li>
	 *            <li>lookupAll(key1, key2, keyN)</li>
	 *            </ul>
	 * @return Liste saemtlicher Eintraege, die einen der Parameter als
	 * Schluessel beinhalten. Die Liste ist aufsteigend nach Schlüsseln sortiert.
	 * Dictionary
	 */
	List<Entry<K, V>> lookupAll (K... key) {
		List<Entry<K, V>> foundEntries = new ArrayList<>();
		for (K k : key) {
			if (isIn(k)) {
				for (Entry<K, V> entry : entries) {
					if (entry.getKey() == k)
						foundEntries.add(entry);
				}
			}
		}
		Collections.sort(foundEntries);
		return foundEntries;
	}
	
	/**
	 * Darstellung des Dictionary als String.<br>
	 * Die einzelnen Eintraege sollen durch ein Komma gefolgt von einem
	 * Leerzeichen getrennt werden. Hinter dem letzten Eintrag kommt keine
	 * weitere Ausgabe.<br>
	 * Beispiel: "schluessel1-&gt->wert1, schluessel2-&gt->wert2,
	 * schluessel3-&gt->wert3"
	 *
	 * @return Stringrepraesentation des Dictionary
	 */
	public String toString () {
		StringBuilder sb = new StringBuilder();
		int counter = 1;
		for (Entry<K, V> entry : entries) {
			sb.append(entry.toString());
			if (counter != entries.size())
				sb.append(", ");
			counter++;
		}
		return sb.toString();
	}
	
	/**
	 * Platz fuer weitere benoetigte Methoden
	 */
	
}
