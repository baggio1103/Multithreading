import static java.lang.Thread.currentThread;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        var thread = new Thread(() -> {
            System.out.println("Thread is executing");
            while (!currentThread().isInterrupted()) {
                try {
                    System.out.println("Hello world");
                    Thread.sleep(2000);
                } catch (Exception exception) {
                    System.out.println(exception);
                    Thread.currentThread().interrupt();
                    System.out.println(Thread.currentThread().isInterrupted());
                }
                System.out.println(currentThread().isInterrupted());
            }
        });
        thread.start();
        System.out.println("Main is executing");
        thread.interrupt();

        var threadTwo = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException exception) {
                System.out.println("Exception occurred");
            }
            System.out.println("Thread-ii is executing");
            throw new RuntimeException("Runtime exception");
        });
        threadTwo.start();
        threadTwo.interrupt();
        System.out.println("Main is executing finally");
    }

}