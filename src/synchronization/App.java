package synchronization;

public class App {

    public static int counter = 0;

    public static int synCounter = 0;

    public static synchronized void increment() {
        synCounter++;
    }

    public static void process() throws InterruptedException {
        Runnable incrementer = () -> {
            for (int i = 0; i < 10000; i++) {
                counter++;
            }
        };

        Runnable synchronizedIncrementer = () -> {
            for (int i = 0; i < 10000; i++) {
                increment();
            }
        };

        Thread threadOne = new Thread(incrementer);
        Thread threadTwo = new Thread(incrementer);
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();

        System.out.println("Threads have executed, the result:  " + counter);

        var synchronizedOne = new Thread(synchronizedIncrementer);
        var synchronizedTwo = new Thread(synchronizedIncrementer);
        synchronizedOne.start();
        synchronizedTwo.start();
        synchronizedOne.join();
        synchronizedTwo.join();
        System.out.println("Synchronized threads have executed, the result: " + synCounter);

    }

    public static void main(String[] args) throws InterruptedException {
        process();
    }

}
