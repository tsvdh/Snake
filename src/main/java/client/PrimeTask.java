package client;

import javafx.concurrent.Task;

import java.util.ArrayList;

public class PrimeTask extends Task<ArrayList<Long>> {

    private Long from;
    private Long to;

    /**
     * Constructs a new PrimeTask.
     *
     * @param from Where to begin looking for primes.
     * @param to Where to end looking for primes.
     */
    public PrimeTask(Long from, Long to) {
        this.from = from;
        this.to = to;
    }

    @Override
    protected ArrayList<Long> call() {
        return primesInRange(from, to);
    }

    /**
     * Calculates primes in the given range.
     *
     * @param from Where to begin looking for primes.
     * @param to Where to end looking for primes.
     * @return An ArrayList with all the primes in the given range.
     */
    public static ArrayList<Long> primesInRange(long from, long to) {
        ArrayList<Long> list = new ArrayList<>();
        for (long i = from; i <= to; i++) {
            if (isPrime(i)) {
                list.add(i);
            }
        }
        return list;
    }

    /**
     * Checks whether a number is prime.
     *
     * @param number The number to check.
     * @return A boolean indicating the result of the check.
     */
    public static boolean isPrime(long number) {
        if (number < 3) {
            return false;
        }
        for (long i = 2; i < number; i++) {
            if (number % i == 0) {
                System.out.println(i);
                return false;
            }
        }
        return true;
    }
}
