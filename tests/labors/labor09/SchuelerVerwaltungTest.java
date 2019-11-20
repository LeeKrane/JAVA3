package labors.labor09;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import static org.junit.Assert.*;

public class SchuelerVerwaltungTest {
    
    private SchuelerVerwaltung verwaltung;
    private Schueler andrae, riegler, ines;

    public SchuelerVerwaltungTest() {
        try {
            verwaltung = new SchuelerVerwaltung("resources/labors/labor09/Schueler_SortName.csv");
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
    public void getSchuelerFromKlasse_klasse1AHIF_success () {
        String klasse = "1AHIF";
        Set<Schueler> result = verwaltung.getSchuelerFromKlasse(klasse);
        assertEquals(36, result.size());
        assertTrue(result.contains(andrae));
        assertTrue(result.contains(riegler));
        result = verwaltung.getSchuelerFromKlasse("3BHIF");
        assertEquals(27, result.size());
        assertTrue(result.contains(ines));
    }
    
    @Test
    public void getSchuelerFromKlasse_klasse3BHIF_success () {
        Set<Schueler> result = verwaltung.getSchuelerFromKlasse("3BHIF");
        assertEquals(27, result.size());
        assertTrue(result.contains(ines));
    }

    @Test
    public void containsName_singleResult_success () {
        Set<Schueler> result = verwaltung.containsName("Andrae", true);
        assertEquals(1, result.size());
        assertTrue(result.contains(andrae));
    }
    
    @Test
    public void containtsName_multipleResults_success () {
        Set<Schueler> result = verwaltung.containsName("Riegler", true);
        Set<Schueler> expected = new TreeSet<>(Set.of(
                Schueler.makeSchueler("1AHIF;Riegler;Marvin;m;01.09.2002;evang. A.B."),
                Schueler.makeSchueler("2CHIF;Riegler;Lukas;m;20.06.2001;röm.-kath."),
                Schueler.makeSchueler("3AHIF;Riegler;Sebastian;m;15.02.2000;röm.-kath.")
        ));
        assertEquals(expected, result);
    }

    @Test
    public void containtsName_subString_success () {
        Set<Schueler> result = verwaltung.containsName("sic", false);
        Set<Schueler> expected = new TreeSet<>(Set.of(
                Schueler.makeSchueler("2CHIF;Presich;Sophie-Marie;w;29.01.2000;evang. A.B."),
                Schueler.makeSchueler("3BHIF;Husic;Senad;m;03.04.2000;islam.")
        ));
        assertEquals(expected, result);
    }
    
    @Test
    public void getAllWith_female_success () {
        Set<Schueler> result = verwaltung.getAllWith('w');
        assertEquals(result.size(), 24);
        List<Schueler> expected = new ArrayList<>(result);
        assertEquals(ines, expected.get(14));
    }
    
    @Test
    public void getAllWith_x_emptySet () {
        assertEquals(Collections.emptySet(), verwaltung.getAllWith('x'));
    }

    @Test
    public void getKlassenAnzahl_success () {
        Map<String, Integer> result = verwaltung.getKlassenAnzahl();
        assertEquals(36, (int) result.get("1AHIF"));
        assertEquals(19, (int) result.get("2BHIF"));
        assertEquals(27, (int) result.get("3AHIF"));
        assertEquals(20, (int) result.get("4CHIF"));
        assertEquals(20, (int) result.get("5BHIF"));
    }

    @Test
    public void getReligionsZugehoerigkeit_success () {
        Map<String, Map<String, List<String>>> result = verwaltung.getReligionsZugehoerigkeit();
        assertEquals(2, result.get("ALEVI").size());
        assertNull(result.get("evang. A. B."));
        assertEquals(8, result.get("evang. A.B.").size());
        assertTrue(result.get("islam.").containsKey("5BHIF"));
        Map<String, List<String>> religionsListe = result.get("islam.");
        assertTrue(religionsListe.get("2BHIF").contains("Harbas"));
        assertEquals(1, religionsListe.get("2BHIF").size());
    }

    @Test
    public void getGeborenBis_beforeNow_success () {
        Set<Schueler> result = verwaltung.getGeborenBis(LocalDate.now(), true);
        assertEquals(324, result.size());
    }
    
    @Test
    public void getGeborenBis_afterNow_emptySet () {
        Set<Schueler> result = verwaltung.getGeborenBis(LocalDate.now(), false);
        assertEquals(Collections.emptySet(), result);
    }
    
    @Test
    public void getGeborenBis_beforeDate_success () {
        Set<Schueler> result = verwaltung.getGeborenBis(LocalDate.of(1999, 7, 1), true);
        assertEquals(120, result.size());
    }
    
    @Test
    public void getGeborenBis_afterDate_success () {
        Set<Schueler> result = verwaltung.getGeborenBis(LocalDate.of(1999, 7, 1), false);
        assertEquals(204, result.size());
    }


    @Test
    public void geburtstagsListe_year2017_success () {
        Map<LocalDate, Set<String>> result = verwaltung.getGeburtstagsListe(2017);
        assertEquals(213, result.size());
        List<Set<String>> names = new ArrayList<>(result.values());
        assertTrue(names.get(5).contains("Mrskos Julia Marlene 1CHIF 15"));
        assertEquals(2, names.get(5).size());
        assertEquals(7, names.get(43).size());
    }
    
    @Test
    public void geburtstagsListe_year1995_success () {
        assertEquals(5, verwaltung.getGeburtstagsListe(1995).size());
    }

    @Test
    public void geburtstagsListe_year1901_emptyMap () {
        assertEquals(Collections.EMPTY_MAP, verwaltung.getGeburtstagsListe(1901));
    }
    
    @Test
    public void geburtstagsListe_success () {
        assertEquals(213, verwaltung.getGeburtstagsListe().size());
    }

}
