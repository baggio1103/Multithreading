package voolatile;

public class App {

    public static void main(String[] args) throws InterruptedException {
        var processor = new Processor();
        var thread = new Thread(processor);
        thread.start();
        Thread.sleep(3000);
        processor.setTerminated(true);
        System.out.println("Processor is terminating...");
    }

}

class Processor implements Runnable {

    private volatile boolean terminated;

    @Override
    public void run() {
        while (!terminated) {
            System.out.println("Processor is executing...");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isTerminated() {
        return terminated;
    }

    public void setTerminated(boolean terminated) {
        this.terminated = terminated;
    }

}