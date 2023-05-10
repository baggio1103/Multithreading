package executor.stoppingexecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executorService.submit(new Worker(i + 1));
        }
        System.out.println("No new tasks accepted!");
        executorService.shutdown();
        try {
            executorService.submit(new Worker(123));
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException exception) {
            executorService.shutdownNow();
        }
        System.out.println(211);
    }

}

class Worker implements Runnable {

    private final int id;

    public Worker(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Task with id: " + id + " is in work - executed by Thread: "  +Thread.currentThread().getName());
        long duration = (long) (Math.random() * 5);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName()  + " " +
                    "has been interrupted. Status: " + Thread.currentThread().isInterrupted() );
        }
    }

}