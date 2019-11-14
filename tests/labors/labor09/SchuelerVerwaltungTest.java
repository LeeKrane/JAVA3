package labors.labor09;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;

public class SchuelerVerwaltungTest {

    private SchuelerVerwaltung informatik;
    private Schueler andrae, riegler, ines;

    public SchuelerVerwaltungTest() {
        try {
            informatik = new SchuelerVerwaltung("resources/labors/labor09/Schueler_SortName.csv");
        } catch (IOException ex) {
            System.err.println("File not found. Should be in project root directory. ");
        }
    }

    @Before
    public void setUp() {
        andrae = Schueler.makeSchueler("1AHIF;Andrae;Franz Eduardo;m;09.12.2001;röm.-kath.");
        riegler = Schueler.makeSchueler("1AHIF;Riegler;Marvin;m;01.09.2002;evang. A.B.");
        ines = Schueler.makeSchueler("3BHIF;Lapatschka;Ines;w;19.03.2000;evang. A.B.");
    }

    @Test
    public void testGetSchuelerFromKlase() {
        String klasse = "1AHIF";
        Set<Schueler> result = informatik.getSchuelerFromKlasse(klasse);
        assertEquals(36, result.size());
        System.out.println(result);
        assertTrue(result.contains(andrae));
        assertTrue(result.contains(riegler));
        result = informatik.getSchuelerFromKlasse("3BHIF");
        assertEquals(27, result.size());
        assertTrue(result.contains(ines));
    }

    @Test
    public void testContainsName() {
        String name = "Andrae";
        Set<Schueler> result = informatik.containsName(name, true);
        assertEquals(1, result.size());
        assertTrue(result.contains(andrae));
        result = informatik.containsName("Riegler", true);
        assertEquals(3, result.size());
        List<Schueler> liste = new ArrayList<>(result);
        assertEquals("Lukas", liste.get(1).getVorname());
        assertEquals("3AHIF", liste.get(2).getKlasse());
        assertEquals("evang. A.B.", liste.get(0).getReligion());
        result = informatik.containsName("mann", false);
        assertEquals(6, result.size());
        liste = new ArrayList<>(result);
        Schueler schueler = liste.get(0);
        assertEquals("Hofmann", schueler.getName());
        schueler = liste.get(3);
        assertEquals("Peter", schueler.getVorname());
        assertEquals(LocalDate.of(1996, 1, 20), liste.get(5).getGeboren());
    }

    @Test
    public void testGetAllWith() {
        Set<Schueler> result = informatik.getAllWith('w');
        assertEquals(result.size(), 24);
        List<Schueler> liste = new ArrayList<>(result);
        assertEquals("Jasmin Juliane Edeltraud", liste.get(1).getVorname());
        assertEquals(LocalDate.of(1998, 5, 24), liste.get(23).getGeboren());
        assertEquals("3BHIF", liste.get(14).getKlasse());
        assertEquals(ines, liste.get(14));
    }

    @Test
    public void testGetKlassenAnzahl() {
        Map<String, Integer> result = informatik.getKlassenAnzahl();
        assertEquals(36, (int) result.get("1AHIF"));
        assertEquals(19, (int) result.get("2BHIF"));
        assertEquals(27, (int) result.get("3AHIF"));
        assertEquals(20, (int) result.get("4CHIF"));
        assertEquals(20, (int) result.get("5BHIF"));
    }

    @Test
    public void testGetReligionsZugehoerigkeit() {
        Map<String, Map<String, List<String>>> result
                = informatik.getReligionsZugehoerigkeit();
        assertEquals(2, result.get("ALEVI").size());
        assertNull(result.get("evang. A. B."));
        assertEquals(8, result.get("evang. A.B.").size());
        assertTrue(result.get("islam.").keySet().contains("5BHIF"));
        Map<String, List<String>> religionsListe = result.get("islam.");
        assertTrue(religionsListe.get("2BHIF").contains("Harbas"));
        assertEquals(1, religionsListe.get("2BHIF").size());
    }

    @Test
    public void testGetGeborenBis() {
        LocalDate heute = LocalDate.now();
        Set<Schueler> result = informatik.getGeborenBis(heute, true);
        assertEquals(324, result.size());
        result = informatik.getGeborenBis(heute, false);
        assertEquals(0, result.size());
        result = informatik.getGeborenBis(LocalDate.of(1999, 7, 1), true);
        assertEquals(120, result.size());
        result = informatik.getGeborenBis(LocalDate.of(1999, 7, 1), false);
        assertEquals(204, result.size());
    }

    @Test
    public void testGeburtstagsListeIntArg() {
        Map<LocalDate, Set<String>> result = informatik.getGeburtstagsListe(2017);
        assertEquals(213, result.size());
        assertEquals(5, informatik.getGeburtstagsListe(1995).size());
        assertEquals(Collections.EMPTY_MAP, informatik.getGeburtstagsListe(1901));
        List<Set<String>> namensListe = new ArrayList<>(result.values());
        assertTrue(namensListe.get(5).contains("Mrskos Julia Marlene 1CHIF 15"));
        assertEquals(2, namensListe.get(5).size());
        assertEquals(7, namensListe.get(43).size());
    }

    @Test
    public void testGeburtstagsListeNoArgs() {
        Map<LocalDate, Set<String>> result = informatik.getGeburtstagsListe();
        assertEquals(213, result.size());
    }

}
