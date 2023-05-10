package executor.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class App {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 1000; i++) {
            int taskId = i;
            executorService.submit( () ->
                    System.out.println("Task with id " + taskId + " is in work by thread " + Thread.currentThread()));
        }
        executorService.shutdown();
        try {
            System.out.println(executorService.submit(() -> System.out.println("Shiit : " + Thread.currentThread().getName())));
        } catch (RejectedExecutionException executionException) {
            System.out.println(executionException);
        }
    }

}
