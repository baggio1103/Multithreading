package visibility;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        var fieldVisibility  = new FieldVisibility();
        var threadOne = new Thread(fieldVisibility::write);
        var threadTwo = new Thread(fieldVisibility::read);
        threadTwo.start();
        Thread.sleep(1000);
        threadOne.start();
    }

}
