package client;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreTest {

    private static int oldScore;

    @BeforeClass
    public static void setUp() {
        oldScore = Score.getScore();
    }

    @AfterClass
    public static void tearDown() {
        Score.setScore(oldScore);
    }

    @Test
    public void setAndGetScore() {
        Score.setScore(42);

        assertEquals(42, Score.getScore());
    }

    @Test
    public void classCall() {
        new Score();
    }

    @Test
    public void wrongPath1() {
        Score.path = "src/test/java/client/testDirectory";

        assertEquals(-1, Score.getScore());

        Score.path = "data/score.txt";
    }

    @Test
    public void wrongPath2() {
        Score.path = "src/test/java/client/testDirectory";

        assertEquals(-1, Score.setScore(42));

        Score.path = "data/score.txt";
    }
}
