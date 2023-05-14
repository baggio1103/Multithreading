package forkjoin.recursivetask;

import java.util.concurrent.RecursiveTask;

public class SimpleRecursiveTask extends RecursiveTask<Integer> {

    private int simulatedWork;

    public SimpleRecursiveTask(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected Integer compute() {
        if (simulatedWork > 100) {
            System.out.println("Processing must parallelized... Value: " + simulatedWork);
            var leftPartition = new SimpleRecursiveTask(simulatedWork / 2);
            var rightPartition = new SimpleRecursiveTask(simulatedWork / 2);
            leftPartition.fork();
            rightPartition.fork();
            return leftPartition.join() + rightPartition.join();
        } else {
            System.out.println(Thread.currentThread().getName());
            System.out.println("No longer division, starting execution... Value: " + simulatedWork);
            return 2 * simulatedWork;
        }
    }

}
