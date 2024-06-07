package chapter02_java_thread_structure.exam01_thread_creation;

public class AnonymousThreadClassExample {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " : 쓰레드 실행중");
            }
        };

        thread.start();
    }
}
