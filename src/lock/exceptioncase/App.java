package lock.exceptioncase;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {

    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();
        Thread threadOne = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadTwo = new Thread(processor::consume, "Thread two");
        threadOne.start();
        Thread.sleep(200);
        threadTwo.start();
        threadTwo.interrupt();
        threadOne.join();
        threadTwo.join();
        System.out.println("Value: " + processor.getValue());
    }

}

class Processor {

    private final Lock lock = new ReentrantLock();
    private int value = 0;

    public void produce() throws InterruptedException {
            lock.lock();
        try {
            System.out.println("Producer started executing... ");
            value++;
        } finally {
            lock.unlock();
        }

    }

    public void consume() {
        lock.lock();
        System.out.println("Consumer started executing...");
        value++;
        lock.unlock();
    }

    public int getValue() {
        return value;
    }
}
