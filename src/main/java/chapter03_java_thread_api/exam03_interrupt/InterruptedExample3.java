package chapter03_java_thread_api.exam03_interrupt;

public class InterruptedExample3 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("쓰레드 작동중");
                if (Thread.interrupted()) {
                    System.out.println("인터럽트 상태가 초기화되었습니다");
                    break;
                }
            }
            System.out.println("인터럽트 상태 : " + Thread.currentThread().isInterrupted());
            Thread.currentThread().interrupt();
            System.out.println("인터럽트 상태 : " + Thread.currentThread().isInterrupted());
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
 * 이 쓰레드를 참조하는 다른 쓰레드에서 interrupt 상태가 false라면 side effect가 발생할 수 있음
 * -> 다시 interrupt 상태를 true로 변경해줌
 */