package diningphilosophersproblem;

import java.util.concurrent.Executors;

import static diningphilosophersproblem.Constants.*;
import static diningphilosophersproblem.Constants.NUMBER_OF_CHOPSTICKS;
import static diningphilosophersproblem.Constants.NUMBER_OF_PHILOSOPHERS;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        var philosophers = new Philosopher[NUMBER_OF_PHILOSOPHERS];
        var chopsticks = new ChopStick[NUMBER_OF_CHOPSTICKS];
        var executorService = Executors.newFixedThreadPool(NUMBER_OF_PHILOSOPHERS);
        try {
            for (int i = 0; i < NUMBER_OF_CHOPSTICKS; i++) {
                chopsticks[i] = new ChopStick(i);
                philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % NUMBER_OF_CHOPSTICKS]);
                executorService.submit(philosophers[i]);
            }
            executorService.shutdown();
            Thread.sleep(DURATION_OF_SIMULATION);
            for (Philosopher philosopher : philosophers) {
                philosopher.setFull(true);
            }
        } finally {
            executorService.shutdown();
            while (executorService.isTerminated()) {
                Thread.sleep(1000);
            }
            for (Philosopher philosopher: philosophers) {
                System.out.println(philosopher +  " ate " + philosopher.getEatingCounter() + " times");
            }
        }
    }

}
