package chapter03_java_thread_api.exam03_interrupt;

public class IsInterruptedExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("쓰레드가 작동중입니다");
            }
            System.out.println("쓰레드가 인터럽트 되었습니다");
            System.out.println("Thread.currentThread().isInterrupted() = " + Thread.currentThread().isInterrupted());
        });
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt();
    }
}

/**
 * while(!Thread.currentThread().isInterrupted()) 에서 벗어났을 때의 thread의 interrupted = true
 * Thread.currentThread().isInterrupted() = true
 */
