package concurrenthashmap;

import java.util.concurrent.Exchanger;

public class App {

    public static void main(String[] args) throws InterruptedException {
        Exchanger<Integer> exchanger = new Exchanger<>();
        var threadOne = new Thread(new Producer(exchanger));
        var threadTwo = new Thread(new Consumer(exchanger));
        threadOne.start();
        threadTwo.start();
        Thread.sleep(2000);
        System.out.println("Is Producer alive ? " + threadOne.isAlive());
        System.out.println("Is Consumer alive ? " + threadTwo.isAlive());
    }

}

class Producer implements Runnable {

    private Exchanger<Integer> exchanger;
    private int counter;

    public Producer(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
        counter = 0;
    }

    @Override
    public void run() {
        while (true) {
            counter++;
            System.out.println("Producer has incremented counter => counter = " + counter);
            try {
                counter = exchanger.exchange(null);
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

}

class Consumer implements Runnable {


    private Exchanger<Integer> exchanger;
    private int counter;

    public Consumer(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
        counter = 0;
    }

    @Override
    public void run() {
        while (true) {
            counter--;
            System.out.println("Consumer has decremented counter => counter = " + counter);
            try {
                counter = exchanger.exchange(counter);
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                throw new RuntimeException(exception);
            }
        }
    }

}