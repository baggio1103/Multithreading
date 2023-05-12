package multithreading.daemon;

public class App {

    public static void main(String[] args) {
        var worker = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            var threadName = Thread.currentThread().getName();
            System.out.println("Thread is finishing its execution with name: " + threadName);
        });

        var daemon = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                var threadName = Thread.currentThread().getName();
                System.out.println("Daemon thread is executing with name: " + threadName);
                System.out.println(Thread.currentThread().isInterrupted());
                System.out.println(Thread.currentThread().isAlive());
            }
        });
        daemon.setDaemon(true);
        worker.start();
        daemon.start();

        var threadName = Thread.currentThread().getName();
        System.out.println("Thread is executing with name: " + threadName);
    }

}