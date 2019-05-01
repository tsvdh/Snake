package client;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class DifficultyTest {

    private static String oldDifficulty;

    @BeforeClass
    public static void setUp() {
        oldDifficulty = Difficulty.getDifficulty();
    }

    @AfterClass
    public static void tearDown() {
        Difficulty.setDifficulty(oldDifficulty);
    }

    @Test
    public void setAndGetDifficulty() {
        Difficulty.setDifficulty("bla");

        assertEquals("bla", Difficulty.getDifficulty());
    }

    @Test
    public void classCall() {
        new Difficulty();
    }

    @Test
    public void wrongPath1() {
        Difficulty.path = "src/test/java/client/testDirectory";

        assertNull(Difficulty.getDifficulty());

        Difficulty.path = "data/difficulty.txt";
    }

    @Test
    public void wrongPath2() {
        Difficulty.path = "src/test/java/client/testDirectory";

        assertEquals(-1, Difficulty.setDifficulty("bla"));

        Difficulty.path = "data/difficulty.txt";
    }
}
