package client;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PrimeTaskTest {

    @Test
    public void isPrime() {
        assertTrue(PrimeTask.isPrime(5));
        assertFalse(PrimeTask.isPrime(4));
        assertFalse(PrimeTask.isPrime(1));
    }

    @Test
    public void primesInRange() {
        ArrayList<Long> list = new ArrayList<>();
        assertEquals(list, PrimeTask.primesInRange(-3, 1));

        list.add(3L);
        list.add(5L);
        list.add(7L);
        assertEquals(list, PrimeTask.primesInRange(0, 8));
    }

    @Test
    public void call() {
        ArrayList<Long> list = new ArrayList<>();
        list.add(11L);
        list.add(13L);
        assertEquals(list, new PrimeTask(10L, 13L).call());
    }
}
