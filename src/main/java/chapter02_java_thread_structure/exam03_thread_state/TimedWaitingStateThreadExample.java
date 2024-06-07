package chapter02_java_thread_structure.exam03_thread_state;

public class TimedWaitingStateThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Object lock = new Object();

        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();
        Thread.sleep(100);
        System.out.println("thread.getState() = " + thread.getState());
    }
}
