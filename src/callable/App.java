package callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class App {

    public static void main(String[] args) throws Exception {
        ExecutorService executors = Executors.newFixedThreadPool(2);
        List<Future<String>> futureList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Future<String> future = executors.submit(new Processor(i + 1));
            futureList.add(future);
        }
        System.out.println("Hello from main");
        Thread.sleep(3000);
        for (Future<String> future : futureList) {
            System.out.println("Has a task been executed? " + (future.isDone()? "Yes" : "No"));
            System.out.println(future.get());
        }
        System.out.println("Finished");
        executors.shutdown();
        FutureTask<String> futureTask = new FutureTask<>(new Processor(12213));
        futureTask.run();
        System.out.println(futureTask.isDone());
        System.out.println("Value: " +  futureTask.get());
    }

}

class Processor implements Callable<String> {

    private final int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(500);
        return "Thread id : " + id;
     }

}
