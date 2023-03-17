package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {

    static int counter = 0;

    private static final Lock lock = new ReentrantLock();

    public static void increment() {
        lock.lock();
        try {
            for (int i = 0; i < 100000; i++) {
                counter++;
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(App::increment);
        Thread threadTwo = new Thread(App::increment);
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
        System.out.println("Result of execution: " + counter);
    }

}
