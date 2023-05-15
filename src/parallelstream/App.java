package parallelstream;

import java.util.stream.IntStream;

public class App {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        long numberOfPrimes = IntStream.rangeClosed(2, Integer.MAX_VALUE/100).filter(App::isPrime).count();
        System.out.println("Number of prime numbers: " + numberOfPrimes);
        System.out.println("Time taken to complete the complete sequentially: " + (System.currentTimeMillis() - start));
        System.out.println("-------------------------------------------------------");
        start = System.currentTimeMillis();
        numberOfPrimes = IntStream.rangeClosed(2, Integer.MAX_VALUE/100).parallel().filter(App::isPrime).count();
        System.out.println("Number of prime numbers: " + numberOfPrimes);
        System.out.println("Time taken to complete the complete in parallel: " + (System.currentTimeMillis() - start));
    }

    public static boolean isPrime(int value) {
        if (value <= 1) {
            return false;
        }
        if (value == 2) {
            return true;
        }
        if (value %2 == 0) {
            return false;
        }
        long maxDivisor = (long) Math.sqrt(value);
        for (int i = 3; i < maxDivisor; i+=2) {
            if (value %i == 0) {
                return false;
            }
        }
        return true;
    }

}
