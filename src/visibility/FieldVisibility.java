package visibility;

public class FieldVisibility {

    int x = 0;
    private boolean flag = true;

    public void write() {
        x = 1;
        flag = false;
    }

    public void read() {
        System.out.println("Flag value: " + flag);
        while (flag) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Hello world");
            System.out.println("Flag value inside value: " + flag);
        }
    }


}
