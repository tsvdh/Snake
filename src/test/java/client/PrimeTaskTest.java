package client;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PrimeTaskTest {

    private static PrimeTask task;

    @BeforeClass
    public static void setUp() {
        task = new PrimeTask();
    }

    @Test
    public void isPrime() {
        assertTrue(task.isPrime(5));
        assertFalse(task.isPrime(4));
        assertFalse(task.isPrime(1));
    }

    @Test
    public void primesInRange() {
        ArrayList<Long> list = new ArrayList<>();
        assertEquals(list, task.primesInRange(-3, 1));

        list.add(3L);
        list.add(5L);
        list.add(7L);
        assertEquals(list, task.primesInRange(0, 8));
    }
}
