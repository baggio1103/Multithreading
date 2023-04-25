package atomicvariables;

import java.util.concurrent.atomic.AtomicInteger;

public class App {

    public static AtomicInteger value = new AtomicInteger();

    public static void increment() {
        for (int i = 0; i < 100000; i++) {
            value.getAndIncrement();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var threadOne = new Thread(App::increment, "Thread-1");
        var threadTwo = new Thread(App::increment, "Thread-2");
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
        System.out.println("Value: " + value);
    }

}
