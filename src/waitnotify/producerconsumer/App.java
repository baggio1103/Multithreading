package waitnotify.producerconsumer;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {
        Processor processor = new Processor();
        Thread threadOne = new Thread(() -> {
            try {
                processor.producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadTwo = new Thread( () -> {
            try {
                processor.consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadOne.start();
        threadTwo.start();
    }

}

class Processor {

    private final List<Integer> list = new ArrayList<>();
    private final static int UPPER_BOUND = 5;
    private final static  int LOWER_BOUND = 0;
    private final Object lock = new Object();
    private  int value = 0;

    public void producer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == UPPER_BOUND) {
                    System.out.println("Waiting for removing items ...");
                    lock.wait();
                } else {
                    list.add(++value);
                    System.out.println("Adding item:" + value);
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }

    public void consumer() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == LOWER_BOUND) {
                    value = 0;
                    System.out.println("Waiting for adding new items...");
                    lock.wait();
                } else {
                    System.out.println("Removing item: " + list.remove(list.size() - 1));
                    lock.notify();
                }
                Thread.sleep(500);
            }
        }
    }
}
