package executor.singlethreaded;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            executor.execute(new Task(i));
        }
        Thread.sleep(1000);
        for (int i = 0; i < 10; i++) {
            new Thread(new Task(i)).start();
        }
        System.out.println("Hello world: " + Thread.currentThread().getName());
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                System.out.println("Shiiit");
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }).start();
        System.out.println("Main");
    }

}

class Task implements Runnable {

    private final int id;

    public Task(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Task with id: " + id + " is being executed by thread: " + Thread.currentThread().getName());
        long duration = (long) (Math.random() * 9);
        try {
            Thread.sleep(duration);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

    }

}
