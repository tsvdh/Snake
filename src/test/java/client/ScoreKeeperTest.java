package client;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ScoreKeeperTest {

    static int oldScore;

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
}
