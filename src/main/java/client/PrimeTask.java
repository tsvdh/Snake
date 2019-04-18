package client;

import java.util.ArrayList;

public class PrimeTask {

    public static void main(String[] args) {
        PrimeTask task = new PrimeTask();
        System.out.println(task.isPrime(10000019));
    }

    public ArrayList<Long> primesInRange(long from, long to) {
        ArrayList<Long> list = new ArrayList<>();
        for (long i = from; i <= to; i++) {
            if (isPrime(i)) {
                list.add(i);
            }
        }
        return list;
    }

    public boolean isPrime(long number) {
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
