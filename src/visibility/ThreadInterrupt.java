package visibility;

public class ThreadInterrupt {

    public static void main(String[] args) throws InterruptedException {
        var threadOne = new Thread(() ->
        {
            var i = 0;
            for (; i < 1_999_999_999; i++) ;
            System.out.println("i value: " + i);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Thread current status: " + Thread.currentThread().isInterrupted());
        }
        );
        threadOne.start();
        threadOne.interrupt();
        System.out.println("Main: " + threadOne.isInterrupted());
        threadOne.join();
    }

}
