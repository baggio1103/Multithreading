public class App {

    private static int valueForIncrement = 0;

    private static int valueForDecrement = 1_000_000;

    private final static Object incrementLock = new Object();

    private final static Object decrementLock = new Object();

    public static void increment() {
        synchronized (incrementLock) {
            for (int i = 0; i < 100_000; i++) {
                valueForIncrement++;
            }
        }
    }

    public static void decrement() {
        synchronized (decrementLock) {
            for (int i = 0; i < 100_000; i++) {
                valueForDecrement--;
            }
        }
    }


    public static void main(String[] args) throws Exception {
        var threadOne = new Thread(App::increment);
        var threadTwo = new Thread(App::decrement);
        threadOne.start();
        threadTwo.start();
        threadOne.join();
        threadTwo.join();
        System.out.println("Incremented Value : " + valueForIncrement);
        System.out.println("Decremented Value : " + valueForDecrement);
    }

}
