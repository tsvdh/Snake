package client;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrimeTaskTest {

    @Test
    public void firstMethod() {
        PrimeTask task = new PrimeTask();
        assertEquals(18, task.firstMethod());
    }
}
