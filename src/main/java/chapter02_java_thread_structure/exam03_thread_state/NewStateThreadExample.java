package chapter02_java_thread_structure.exam03_thread_state;

public class NewStateThreadExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("쓰레드 실행중");
        });

        System.out.println("thread.getState() = " + thread.getState());
    }
}
