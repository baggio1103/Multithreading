package executor.fixedthreadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            executor.execute(new Work(i+1));
        }
        System.out.println("Executor is executing the tasks");
    }

}

class Work implements Runnable {

    private final Integer id;

    public Work(Integer id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Task with id: " + id + " is in process by thread " + Thread.currentThread().getName());
        try {
            Thread.sleep(500);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }

}
