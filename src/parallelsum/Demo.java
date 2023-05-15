package parallelsum;

import java.util.Random;

public class Demo {

    public static void main(String[] args) {
        var random = new Random();
        int[] values = new int[500000000];
        for (int i = 0; i < 500000000; i++) {
            values[i] = random.nextInt(1000);
        }
        var sequentialSum = new SequentialSum();
        var start = System.currentTimeMillis();
        var sum = sequentialSum.sum(values);
        var end = System.currentTimeMillis();
        System.out.println("Sum: " + sum + ", Overall execution time: " + (end - start));
    }

}
