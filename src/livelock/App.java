package livelock;

import java.util.concurrent.TimeUnit;
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
        while (true) {
            lockOne.tryLock(500, TimeUnit.MILLISECONDS);
            System.out.println("Producer acquired the lock#1");
            System.out.println("Producer tries to acquire the lock#2");
            if (lockTwo.tryLock()) {
                System.out.println("Producer acquired the lock#2");
                lockTwo.unlock();
            } else {
                System.out.println("Producer cannot acquire the lock#2");
                continue;
            }
            break;
        }
        lockOne.unlock();
    }

    public void consume() throws InterruptedException {
        while (true) {
            lockTwo.tryLock(500, TimeUnit.MILLISECONDS);
            System.out.println("Consumer acquired the lock#2");
            System.out.println("Consumer tries to acquire the lock#1");
            if (lockOne.tryLock()) {
                System.out.println("Consumer acquired the lock#1");
                lockOne.unlock();
            } else {
                System.out.println("Consumer cannot acquire the lock#1");
                continue;
            }
            break;
        }
        lockTwo.unlock();
    }


}