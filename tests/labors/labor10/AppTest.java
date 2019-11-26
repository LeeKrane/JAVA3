package labors.labor10;

import org.junit.AfterClass;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

public class AppTest {
/*
    @AfterClass
    public static void deleteTestFiles() throws IOException {
        Files.deleteIfExists(Paths.get("test.dat"));
    }

    @Test
    public void createFile_5values_fileAsSpecified() throws IOException {
        App.createFile("test.dat", 1.2, 3.141592, 4, 7.8, 2.683);
        byte[] result = Files.readAllBytes(Paths.get("test.dat"));
        byte[] expected = Files.readAllBytes(Paths.get("valid.dat"));
        assertArrayEquals(expected, result);
    }

    @Test
    public void isValidFile_validFile_true() throws IOException {
        assertTrue(App.isValidFile("valid.dat"));
    }

    @Test
    public void isValidFile_fileContainingNaNs_true() throws IOException {
        assertTrue(App.isValidFile("nan.dat"));
    }

    @Test
    public void isValidFile_wrongCount_false() throws IOException {
        assertFalse(App.isValidFile("invalid_1.dat"));
    }

    @Test
    public void isValidFile_fileSizeWrong_false() throws IOException {
        assertFalse(App.isValidFile("invalid_2.dat"));
    }

    @Test
    public void printFileInfo_validFile_stringFormattedAsSpecified() throws IOException {
        String result = App.getFileInfo("valid.dat");
        String[] lines = result.split("\n");
        assertTrue(lines[0].contains("5"));
        String expected = "1,20 3,14 4,00 7,80 2,68";
        assertEquals(expected, lines[1]);
    }

    @Test
    public void printFileInfo_fileContainingNaNs_stringFormattedAsSpecified() throws IOException {
        String result = App.getFileInfo("nan.dat");
        String[] lines = result.split("\n");
        assertTrue(lines[0].contains("3"));
        String expected = "Infinity -Infinity NaN";
        assertEquals(expected, lines[1]);
    }

    @Test
    public void printFileInfo_invalidFile_Stringinvalid() throws IOException {
        String result = App.getFileInfo("invalid_1.dat");
        assertTrue(result.contains("invalid"));
    }

    @Test
    public void append_validFile_countUpdatedValueAppended() throws IOException {
        App.createFile("test.dat", 1, 2);
        double toAppend = Math.E;
        App.append("test.dat", toAppend);
        assertTrue(App.isValidFile("test.dat"));
        try (DataInputStream dis = new DataInputStream(new FileInputStream("test.dat"))) {
            assertEquals(3, dis.readInt());
            dis.skipNBytes(16);
            assertEquals(toAppend, dis.readDouble(), 1e-15);
        }
    }

    @Test
    public void append_invalidFile_exception() throws IOException {
        try {
            App.append("invalid_1.dat", 0);
            fail();
        } catch (IllegalArgumentException expected) {
            assertTrue(expected.getMessage().contains("invalid_1.dat"));
            assertTrue(expected.getMessage().contains("invalid"));
        }
    }

    @Test
    public void getContents_validFile_savedNumbers() throws IOException {
        List<Number> result = App.getContents("numbers.dat");
        assertNotNull(result);
        List<Number> expected = List.of(3, 3.141592653589793, 0, -2, 2.718281828459045, 1.41);
        assertEquals(expected, result);
    }

    @Test
    public void getContents_invalidFile_exception() throws IOException {
        try {
            App.getContents("invalid_1.dat");
            fail();
        } catch (IllegalArgumentException expected) {
            assertTrue(expected.getMessage().contains("invalid_1.dat"));
            assertTrue(expected.getMessage().contains("invalid"));
        }
    }

    @Test
    public void groupByType_validFile_valuesGroupedByClass() {
        List<Number> input = List.of(3, 3.141592653589793, 0, -2, 2.718281828459045, 1.41);
        Map<String, Set<Number>> result = App.groupByType(input);
        assertNotNull(result);
        Set<Integer> ints = new TreeSet<>(Set.of(-2, 0, 3));
        Set<Double> doubles = new TreeSet<>(Set.of(1.41, 2.718281828459045, 3.141592653589793));
        Map<String, Set<? extends Number>> expected = Map.of("Integer", ints, "Double", doubles);
        assertEquals(expected, result);
    }
    
 */
}