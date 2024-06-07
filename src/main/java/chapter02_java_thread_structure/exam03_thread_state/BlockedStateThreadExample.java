package chapter02_java_thread_structure.exam03_thread_state;

public class BlockedStateThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                while (true) {

                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("락을 획득하려고 시도");
            }
        });

        thread1.start();
        Thread.sleep(100);
        thread2.start();
        Thread.sleep(100);
        System.out.println("thread2.getState() = " + thread2.getState());
    }
}
