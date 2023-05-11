package delayedqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<DelayedProcessor> queue = new DelayQueue<>();
        queue.put(new DelayedProcessor(1000, "Hello, this is the first message!"));
        queue.put(new DelayedProcessor(20000, "Hello, this is the second message!"));
        queue.put(new DelayedProcessor(4000, "Hello, this is the third message!"));
        while (!queue.isEmpty()) {
            DelayedProcessor processor = queue.take();
            System.out.println(processor);
        }
        System.out.println("Processing finished!");
    }

}

class DelayedProcessor implements Delayed {

    private final long duration;

    private final String message;

    public DelayedProcessor(long duration, String message) {
        this.duration = System.currentTimeMillis() + duration;
        this.message = message;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(duration, ((DelayedProcessor) o).getDuration());
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return message;
    }

}
