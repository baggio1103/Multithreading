package parallelsum;

public class SequentialSum {

    public int sum(int[] array) {
        int total = 0;
        for (int value : array) {
            total += value;
        }
        return total;
    }


}
