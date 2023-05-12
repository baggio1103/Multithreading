package semaphores;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class App {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            executorService.execute(() -> {
                try {
                    Downloader.INSTANCE.downloadData();
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }

}

enum Downloader {

    INSTANCE;

    private final Semaphore semaphore = new Semaphore(5, true);

    public void downloadData() throws InterruptedException {
        semaphore.acquire();
        download();
        semaphore.release();
    }

    private void download() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is executing; " + "Downloading data...");
        Thread.sleep(500);
    }

}
