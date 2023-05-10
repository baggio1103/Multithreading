package executor.scheduledthreadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

class StockMarketAnalyser implements Runnable {

    @Override
    public void run() {
        System.out.println("Downloading the latest stock data from the web...");
        System.out.println(Thread.currentThread().getName());
    }

}

public class App {

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(new StockMarketAnalyser(), 0, 1, MILLISECONDS);
    }

}
