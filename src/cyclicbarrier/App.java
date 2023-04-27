package cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) throws InterruptedException {
        var executors = Executors.newFixedThreadPool(6);
        var random = new Random();
        var barrier = new CyclicBarrier(6, () -> {
            System.out.println("All the threads have executed...");
            System.out.println("Time to generate overall analytics...");
        });
        for (int i = 0; i < 10; i++) {
            executors.execute(new Processor(i + 1, barrier, random));
        }
        Thread.sleep(4000);
        new Thread(new Processor(2121, barrier, random)).start();
        new Thread(new Processor(222, barrier, random)).start();
        System.out.println("Hello world from main");
        executors.shutdown();
//        new Thread(new Processor(2121, barrier, random)).start();
//        new Thread(new Processor(122, barrier, random)).start();
//        new Thread(new Processor(1522, barrier, random)).start();
//        new Thread(new Processor(2141, barrier, random)).start();
//        new Thread(new Processor(132, barrier, random)).start();
//        new Thread(new Processor(1922, barrier, random)).start();

    }

}

class Processor implements Runnable {

    private final int id;
    private final CyclicBarrier barrier;
    private final Random random;

    Processor(int id, CyclicBarrier barrier, Random random) {
        this.id = id;
        this.barrier = barrier;
        this.random = random;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Task with id = " + id + " is in work by thread : " + Thread.currentThread().getName());
        try {
            Thread.sleep(random.nextInt(3000));
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        try {
            barrier.await();
        } catch (InterruptedException | BrokenBarrierException exception) {
            exception.printStackTrace();
        }
        System.out.println("Hello ");
    }


}
