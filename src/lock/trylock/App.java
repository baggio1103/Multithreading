package lock.trylock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {

    public static void main(String[] args) throws InterruptedException {
        var executor = new Executor();
        var threadOne = new Thread(() -> {
            try {
                executor.execute();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });
        var threadTwo = new Thread(() -> {
            try {
                executor.execute();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
        System.out.println("Value: " + executor.getValue());
    }

}

class Executor {

    private final Lock lock = new ReentrantLock();
    private int value = 0;

    public void execute() throws InterruptedException {
        boolean b = lock.tryLock();
        System.out.println("Acquired lock? " + b);
//        lock.lock();
//        System.out.println("Acquired lock");
        Thread.sleep(1000);
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 1000; i++) {
            value++;
        }
        lock.unlock();
    }

    public int getValue() {
        return value;
    }

}
