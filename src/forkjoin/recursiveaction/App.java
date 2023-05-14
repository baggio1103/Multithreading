package forkjoin.recursiveaction;

import java.util.concurrent.ForkJoinPool;

public class App {

    public static void main(String[] args) throws InterruptedException {
        var pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
        var simpleRecursiveAction = new SimpleRecursiveAction(500);
        pool.invoke(simpleRecursiveAction);
    }

}
