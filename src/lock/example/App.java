package lock.example;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {

    public static void main(String[] args) throws InterruptedException {
        var processor = new Processor();
        var threadOne = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });
        var threadTwo = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });
        threadOne.start();
        Thread.sleep(500);
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
    }

}

class Processor {

    private final Lock lock = new ReentrantLock(true);
    private final Condition condition = lock.newCondition();
    private int value = 0;

    public void produce() throws InterruptedException {
        lock.lock();
        System.out.println("Producer is executing, value: " + value);
        value++;
        System.out.println("Value has been incremented");
        condition.await();
        System.out.println("SIGNAL RECEIVED. Producer continue executing...");
        lock.unlock();
    }

    public void consume() throws InterruptedException {
        lock.lock();
        System.out.println("Consumer started executing, value: " + value);
        value++;
        System.out.println("Consumer has incremented value, value: " + value);
        condition.signal();
        Thread.sleep(2000);
        System.out.println("Signal sent");
        lock.unlock();
        Thread.sleep(2000);
        System.out.println("Hello world");
    }

}