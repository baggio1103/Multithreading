package lock.producerconsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {

    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();
        var threadOne = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        var threadTwo = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadOne.start();
        threadTwo.start();
    }

}

class Processor {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private int value = 0;

    public void produce() throws InterruptedException {
        lock.lock();
        System.out.println("Producer is working; value = " + value);
        value++;
        System.out.println("Value is incremented; value = " + value);
        System.out.println("Sending signal to consumer!...");
        condition.await();
        System.out.println("----------------------------------------");
        System.out.println("Producer reacquired the lock, continuing execution");
        System.out.println("Value = " + value);
        lock.unlock();
    }

    public void consume() throws InterruptedException {
        Thread.sleep(1000);
        lock.lock();
        System.out.println("----------------------------------------");
        System.out.println("Consumer started executing. Value: " + value);
        value++;
        System.out.println("Value is incremented, value = " + value);
        condition.signal();
        System.out.println("qwerty");
        lock.unlock();
    }

}
