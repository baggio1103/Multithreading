package forkjoin.recursivetask;

import java.util.concurrent.ForkJoinPool;

public class App {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        var simpleRecursiveTask = new SimpleRecursiveTask(1600);
        var result = forkJoinPool.invoke(simpleRecursiveTask);
        System.out.println("Result of the computation: " + result);
    }

}
