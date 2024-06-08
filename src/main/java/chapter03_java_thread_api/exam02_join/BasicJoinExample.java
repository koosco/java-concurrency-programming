package chapter03_java_thread_api.exam02_join;

public class BasicJoinExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                System.out.println("쓰레드가 3초 동안 작동합니다");
                Thread.sleep(3000);
                System.out.println("쓰레드 작동 완료");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        thread.join();
        System.out.println("자식 쓰레드가 종료된 후 main 쓰레드 실행");
    }
}

/**
 * main 쓰레드가 자식 쓰레드의 작업이 완료될 때까지 대기했다가 main 쓰레드의 작업을 수행하도록 작성
 */
