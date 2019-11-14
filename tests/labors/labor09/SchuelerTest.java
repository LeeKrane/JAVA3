package labors.labor09;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.time.LocalDate;
import java.time.Month;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
/**
 * @author scre
 */
public class SchuelerTest {
    
    Schueler andrae, riegler, ines;
    
    public SchuelerTest () {
    }
    
    
    @Before
    public void setUp () {
        andrae = Schueler.makeSchueler("1AHIF;Andrae;Franz Eduardo;m;29.02.2000;röm.-kath.");
        riegler = Schueler.makeSchueler("1AHIF;Riegler;Marvin;m;01.09.2002;evang. A.B.");
        ines = Schueler.makeSchueler("3BHIF;Lapatschka;Ines;w;19.03.2000;evang. A.B.");
    }
    
    /**
     * Test of makeSchueler method, of class Schueler.
     */
    /**
     * Test of getAge method, of class Schueler.
     */
    @Test
    public void testGetAge () {
        System.out.println("getAge");
        LocalDate date = LocalDate.of(2004, Month.FEBRUARY, 29);
        Schueler instance = andrae;
        int expResult = 4;
        int result = instance.getAge(date);
        assertEquals(expResult, result);
    }

}
