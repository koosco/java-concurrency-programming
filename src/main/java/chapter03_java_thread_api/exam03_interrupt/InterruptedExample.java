package chapter03_java_thread_api.exam03_interrupt;

public class InterruptedExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (!Thread.interrupted()) {
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
 * while(!Thread.interrupted()) 에서 벗어났을 때 thread의 interrupted = false
 * Thread.currentThread().isInterrupted() = false
 * 상태를 다시 초기화해주어야 한다
 */