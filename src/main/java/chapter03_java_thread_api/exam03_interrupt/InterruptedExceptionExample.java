package chapter03_java_thread_api.exam03_interrupt;

public class InterruptedExceptionExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            System.out.println("인터럽트 상태 1 : " + Thread.currentThread().isInterrupted());
            // 아직 interrupted 되지 않았으므로 false
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("쓰레드가 인터럽트되었습니다");
                System.out.println("인터럽트 상태 3 : " + Thread.currentThread().isInterrupted());
                // interrupt가 걸려서 true가 되었다가 예외로 빠져서 false가 된 상태
                Thread.currentThread().interrupt();
                // 다시 thread에 interrupt를 걸어서 true로 만든 상태
            }
        });

        thread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.interrupt(); // interrupt를 걸면 interrupted가 true가 된다.
        // interrupted 되었다가 대기에서 빠져나와 예외구문으로 가면 다시 false가 된다
        thread.join();
        System.out.println("인터럽트 상태 4 : " + thread.isInterrupted());
    }
}
