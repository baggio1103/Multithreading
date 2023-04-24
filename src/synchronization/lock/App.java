package synchronization.lock;

public class App {

    static int counter1 = 0;

    static int counter2 = 0;

    private static final Object lock1 = new Object();

    private static final Object lock2 = new Object();

    static void increment1() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName());
            counter1++;
        }
    }

    static void increment2() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName());
            counter2++;
        }
    }

    public static void process() throws InterruptedException {
        var start = System.currentTimeMillis();
        Runnable runnable1 = () -> {
            for (int i = 0; i < 100; i++) {
                increment1();
            }
        };
        Runnable runnable2 = () -> {
            for (int i = 0; i < 100; i++) {
                increment2();
            }
        };

        Thread threadOne = new Thread(runnable1);
        Thread threadTwo = new Thread(runnable2);

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();
        var end = System.currentTimeMillis();
        System.out.println("Result of execution in ms: " + (end - start));
        System.out.println("RESULT OF INCREMENT1: " + counter1);
        System.out.println("RESULT OF INCREMENT2: " + counter2);
    }

    public static void main(String[] args) throws InterruptedException {
        process();
    }

}
