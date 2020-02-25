package labors.labor09;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.*;

public class SchuelerTest {
    
    @Test
    public void constructor_illegalMonth_exception () {
        assertThrows(DateTimeParseException.class, () -> Schueler.makeSchueler("1AHIF;Anders;Franz Eduardo;m;29.13.1998;röm.-kath."));
    }
    
    /**
     * DateTimeFormatter versucht standardgemäß mit Resolverstyle.SMART falsche Daten zu korrigieren
     */
    @Test
    public void constructor_noLeapYear_exception () {
        assertThrows(DateTimeParseException.class, () -> Schueler.makeSchueler("1AHIF;Anders;Franz Eduardo;m;29.02.1998;röm.-kath."));
    }

    @Test
    public void getAge_dateInFuture_success () {
        LocalDate date = LocalDate.of(2004, Month.FEBRUARY, 29);
        Schueler schueler = Schueler.makeSchueler("1AHIF;Raxler;Martin;m;01.09.1998;evang. A.B.");
        assertEquals(5, schueler.getAge(date));
    }
    
    @Test
    public void getAge_dateInPast_exception () {
        LocalDate date = LocalDate.of(1998, Month.AUGUST, 31);
        try {
            Schueler schueler = Schueler.makeSchueler("1AHIF;Raxler;Martin;m;01.09.1998;evang. A.B.");
            schueler.getAge(date);
            fail("Student not born yet");
        } catch (IllegalArgumentException expected) {
            assertTrue(expected.getMessage().contains(date.toString()));
        }
    }
    
    @Test
    public void getAge_dateEqualBirthdate_success () {
        LocalDate date = LocalDate.of(1998, Month.SEPTEMBER, 1);
        Schueler schueler = Schueler.makeSchueler("1AHIF;Raxler;Martin;m;01.09.1998;evang. A.B.");
        assertEquals(0, schueler.getAge(date));
    }

}
