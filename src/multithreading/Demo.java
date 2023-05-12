package multithreading;

public class Demo {

    public static void main(String[] args) {
        var start = System.currentTimeMillis();
        Runnable runnable = new Runnable();
        Runnable1 runnable1 = new Runnable1();
        runnable.execute();
        runnable1.execute();
        var end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}

class Runnable {

    public void execute() {
        for (int i = 0; i < 10; i ++) {
            System.out.println("Runnable1 " +  i);
        }
    }

}

class Runnable1 {

    public void execute() {
        for (int i = 0; i < 10; i ++) {
            System.out.println("Runnable2 " +  i);
        }
    }

}
