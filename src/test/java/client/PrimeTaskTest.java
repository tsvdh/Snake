package client;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PrimeTaskTest {

    private static PrimeTask primeTask;

    @BeforeClass
    public static void setUp() {
        primeTask = new PrimeTask(0L, 0L);
    }

    @Test
    public void isPrime() {
        assertTrue(primeTask.isPrime(5));
        assertFalse(primeTask.isPrime(4));
        assertFalse(primeTask.isPrime(1));
    }

    @Test
    public void primesInRange() {
        ArrayList<Long> list = new ArrayList<>();
        assertEquals(list, primeTask.primesInRange(-3, 1));

        list.add(3L);
        list.add(5L);
        list.add(7L);
        assertEquals(list, primeTask.primesInRange(0, 8));
    }

    @Test
    public void call() {
        ArrayList<Long> list = new ArrayList<>();
        list.add(11L);
        list.add(13L);
        assertEquals(list, new PrimeTask(10L, 13L).call());
    }
}
