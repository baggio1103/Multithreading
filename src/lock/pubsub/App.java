package lock.pubsub;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class App {

    public static void main(String[] args) {
        var processor = new Processor();
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

    private final int LOWER_LIMIT = 0;
    private final int UPPER_LIMIT = 5;
    private int value = 0;
    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private final List<Integer> list = new ArrayList<>();
    private final long start = System.currentTimeMillis();
    public void produce() throws InterruptedException {
        lock.lock();
        while (ifSthHappens()) {
            if (list.size() == UPPER_LIMIT) {
                System.out.println("Waiting items to be consumed...");
                System.out.println("Passing lock to consumer...");
                condition.await();
            } else {
                list.add(++value);
                System.out.println("Adding new item: " + value);
                condition.signal();
            }
            Thread.sleep(500);
        }
        lock.unlock();
    }

    public void consume() throws InterruptedException {
        lock.lock();
        while (ifSthHappens()) {
            if (list.size() == LOWER_LIMIT) {
                System.out.println("Waiting items to be produced...");
                System.out.println("Passing lock to producer");
                condition.await();
            } else {
                System.out.println("Removing item: " + list.remove(list.size() - 1));
                condition.signal();
            }
            Thread.sleep(500);
        }
        lock.unlock();
    }

    private boolean ifSthHappens() {
        var end = System.currentTimeMillis();
        return (end - start) < 20000;
    }

}