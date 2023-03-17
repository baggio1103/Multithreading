package lock.interruptedly;

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
        Thread threadTwo = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        });
        threadOne.start();
        Thread.sleep(1000);
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
        System.out.println("Thread-name: " + Thread.currentThread().getName());
        lock.lock();
        System.out.println("Lock acquired by producer");
        Thread.sleep(2000);
        try {
            System.out.println("Producer started executing... ");
            value++;
        } finally {
            System.out.println("Lock released by producer");
            lock.unlock();
        }
    }

    public void consume() throws InterruptedException {
        System.out.println("Consumer started executing..." + Thread.currentThread().isInterrupted()) ;
        try {
            lock.lock();
            System.out.println(Thread.currentThread().isInterrupted() + " Current thread");
            System.out.println("Lock acquired by consumer");
        } catch (Exception exception) {
            System.out.println("Exception happened\n" +  exception.getMessage() + " >?");
            throw exception;
        }
        System.out.println("Shiit");
        value++;
        lock.unlock();
        System.out.println("Lock released by consumer");
    }

    public int getValue() {
        return value;
    }

}
