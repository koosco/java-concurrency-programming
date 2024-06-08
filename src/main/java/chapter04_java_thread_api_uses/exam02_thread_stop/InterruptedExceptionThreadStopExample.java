package chapter04_java_thread_api_uses.exam02_thread_stop;

public class InterruptedExceptionThreadStopExample {
    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("작업 쓰레드가 실행중");
                    System.out.println("인터럽트 상태 1 : " + Thread.currentThread().isInterrupted());
                    Thread.sleep(50);
                }
            } catch (InterruptedException e) {
                // interrupt 상태가 true가 되었다가 예외 처리로 넘어오면서 false가 됨
                System.out.println("인터럽트 상태 2 : " + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
            }

            System.out.println("작업 쓰레드가 종료되었습니다");
            System.out.println("인터럽트 상태 3 : " + Thread.currentThread().isInterrupted());
        });

        Thread stopper = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            worker.interrupt();
            System.out.println("중단 쓰레드가 작업 쓰레드를 중단시켰습니다");
        });

        worker.start();
        stopper.start();
    }
}
