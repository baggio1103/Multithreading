package latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) throws InterruptedException {
        var countDownLatch = new CountDownLatch(5);
        var executorsService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            executorsService.execute(new Processor(i + 1, countDownLatch));
        }
        System.out.println("Processes started...");
        Thread.sleep(1200);
        System.out.println(countDownLatch.getCount());
        countDownLatch.await();
        System.out.println("Tasks finished");
        executorsService.shutdown();
    }

}

class Processor implements Runnable {

    private final int id;
    private final CountDownLatch countDownLatch;

    public Processor(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        doWork();
    }

    private void doWork() {
        System.out.println("Thread with id = " + id + " being executed by" + Thread.currentThread().getName() +  " is working...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        countDownLatch.countDown();
    }

}
