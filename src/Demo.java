public class Demo {

    public static void main(String[] args) throws Exception {
        Runnable runnable = () -> {
            while (true) {
                System.out.println(Thread.currentThread().isInterrupted() +  " ?");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        };
        var thread = new Thread(runnable);
        thread.start();
        thread.interrupt();
    }

}
