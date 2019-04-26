package client;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreKeeperTest {

    private static int oldScore;

    @BeforeClass
    public static void setUp() {
        oldScore = ScoreKeeper.getScore();
    }

    @AfterClass
    public static void tearDown() {
        ScoreKeeper.setScore(oldScore);
    }

    @Test
    public void setAndGetScore() {
        ScoreKeeper.setScore(42);

        assertEquals(42, ScoreKeeper.getScore());
    }

    @Test
    public void classCall() {
        ScoreKeeper scoreKeeper = new ScoreKeeper();
    }

    @Test
    public void wrongPath1() {
        ScoreKeeper.path = "bla";

        assertEquals(-1, ScoreKeeper.getScore());

        ScoreKeeper.path = "data/score.txt";
    }

    @Test
    public void wrongPath2() {
        ScoreKeeper.path = "bla";

        assertEquals(-1, ScoreKeeper.getScore());

        ScoreKeeper.path = "data/score.txt";
    }
}
