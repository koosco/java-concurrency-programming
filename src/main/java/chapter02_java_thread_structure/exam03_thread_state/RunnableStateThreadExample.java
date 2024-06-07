package chapter02_java_thread_structure.exam03_thread_state;

public class RunnableStateThreadExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getState());
        });

        thread.start();
    }
}
