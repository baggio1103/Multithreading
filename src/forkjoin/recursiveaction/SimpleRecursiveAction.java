package forkjoin.recursiveaction;

import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveAction extends RecursiveAction {

    private int simulatedWork;

    public SimpleRecursiveAction(int simulatedWork) {
        this.simulatedWork = simulatedWork;
    }

    @Override
    protected void compute() {
        if (simulatedWork > 100) {
            System.out.println("Parallel execution and splitting tasks... ");
            var leftPartition = new SimpleRecursiveAction(simulatedWork / 2);
            var rightPartition = new SimpleRecursiveAction(simulatedWork / 2);
            leftPartition.fork();
            rightPartition.fork();
            leftPartition.join();
            rightPartition.join();
        } else {
            System.out.println("No need for parallel execution. " +
                    "Starting sequential execution. SimulatedWork: " + simulatedWork);
        }
    }

}
