package chapter04_java_thread_api_uses.exam02_thread_stop;

public class InterruptedThreadStopExample {
    public static void main(String[] args) throws InterruptedException {
        Thread worker = new Thread(() -> {
            while (!Thread.interrupted()) {
                // Thread.currentThread().isInterrupted()와 다르게 interrupt 상태가 false가 됨
                System.out.println("작업 쓰레드가 실행중");
            }
            System.out.println("인터럽트 상태: " + Thread.currentThread().isInterrupted());
            System.out.println("작업 쓰레드가 중단되었습니다");
        });

        Thread stopper = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            worker.interrupt();
            System.out.println("중단 쓰레드가 작업 쓰레드를 중단시켰습니다");
        });

        worker.start();
        stopper.start();

        worker.join();
        stopper.join();
    }
}
