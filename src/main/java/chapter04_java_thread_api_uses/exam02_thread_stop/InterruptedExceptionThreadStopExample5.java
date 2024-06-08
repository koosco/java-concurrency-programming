package chapter04_java_thread_api_uses.exam02_thread_stop;

public class InterruptedExceptionThreadStopExample5 {
    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            try {
                while (true) {
                    System.out.println("작업 쓰레드가 실행중");
                    System.out.println("인터럽트 상태 1 : " + Thread.currentThread().isInterrupted());
                    if (Thread.interrupted()) {
                        throw new InterruptedException("쓰레드가 인터럽트 되었습니다");
                    }
                }
            } catch (InterruptedException e) {
                // interrupt 상태가 초기화되지 않음
                // api를 사용할 때 상태가 초기화되는 것이지 임의로 예외를 던지게 되면 상태가 초기화되지 않는다
                System.out.println("인터럽트 상태 2 : " + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt(); // Thread.interrupted API를 사용할 때는 interrupt 상태를 다시 변경해주어야 한다
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
            System.out.println("###############################");
            System.out.println("중단 쓰레드가 작업 쓰레드를 중단시켰습니다");
            System.out.println("###############################");
        });

        worker.start();
        stopper.start();
    }
}
