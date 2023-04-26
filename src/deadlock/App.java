package deadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {

    public static void main(String[] args) {
        var processor = new Processor();
        new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }).start();
    }

}

class Processor {

    private final Lock lockOne = new ReentrantLock();
    private final Lock lockTwo = new ReentrantLock();

    public void produce() throws InterruptedException {
        lockOne.lock();
        System.out.println("Producer acquired the lock#1");
        Thread.sleep(500);
        lockTwo.lock();
        System.out.println("Producer acquired the lock#2");
        lockOne.unlock();
        lockTwo.unlock();
    }

    public void consume() throws InterruptedException {
        lockTwo.lock();
        System.out.println("Consumer acquired the lock#2");
        Thread.sleep(500);
        lockOne.lock();
        System.out.println("Consumer acquired the lock#1");
        lockOne.unlock();
        lockTwo.unlock();
    }

}