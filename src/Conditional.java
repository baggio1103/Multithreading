import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Conditional {

    private static int counter = 0;
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        var threadOne = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("Acquired the lock");
                counter++;
                System.out.println("Counter: " + counter);
                condition.await();
                System.out.println("Question is here");
            } catch (Exception e) {
                lock.unlock();
            }
        });
        var threadTwo = new Thread(() -> {
            lock.unlock();
            System.out.println("Counter: " + counter);
        });
        threadOne.start();
        threadTwo.start();
        Thread.sleep(2000);
        lock.lock();
        condition.signal();
        System.out.println("Signal");
        Thread.sleep(500);
        lock.unlock();
        System.out.println("Released");
    }


}
