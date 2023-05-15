package studentlibrary;

import java.util.concurrent.Executors;

import static studentlibrary.Constant.NUMBER_OF_BOOKS;
import static studentlibrary.Constant.NUMBER_OF_STUDENTS;

public class Demo {

    public static void main(String[] args) {
        var books = new Book[NUMBER_OF_BOOKS];
        var students = new Student[NUMBER_OF_STUDENTS];
        var executorService = Executors.newFixedThreadPool(NUMBER_OF_STUDENTS);
        try {
            for (int i = 0; i < NUMBER_OF_BOOKS; i++) {
                books[i] = new Book(i);
            }
            for (int i = 0; i < NUMBER_OF_STUDENTS; i++) {
                students[i] = new Student(i, books);
                executorService.execute(students[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            executorService.shutdown();
        } finally {
            executorService.shutdown();
        }
    }

}
