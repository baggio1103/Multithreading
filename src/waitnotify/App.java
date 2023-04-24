package waitnotify;

public class App {

    public static void main(String[] args) {
        Processor processor = new Processor();
        Thread threadOne = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread threadTwo = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadOne.start();
        threadTwo.start();
        new Thread(processor::printInfo).start();
    }

}

class Processor {

    int value = 1;

    void printInfo() {
        System.out.println(" I don't give a fuck to other threads ");
    }

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Initial value: " + value);
            value++;
            System.out.println("Producer is executing, incrementing value\n Value = " + value);
            wait();
            System.out.println("-------------------------");
            System.out.println("Continuing executing after passing the lock to another thread." +
                    " The lock has been acquired");
            System.out.println("Value: " + value);
            notify();
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("-------------------------");
            System.out.println("Consumer started executing");
            System.out.println("Value: " + value);
            value++;
            notify();
            System.out.println("Value is incremented, value = " + value);
            notify();
            System.out.println("Releasing the lock");
            notify();
            System.out.println("Notification sent");
            wait();
            System.out.println("-------------------------");
            System.out.println("Final step executed successfully");
        }
    }

}
