package chapter04_java_thread_api_uses.exam03_daemonthread;

public class UserThreadLifeCycleExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("사용자 쓰레드 1 실행중 ..");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("사용자 쓰레드 1 종료");
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("사용자 쓰레드 2 실행중 ..");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("사용자 쓰레드 2 종료");
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        System.out.println("모든 사용자 쓰레드 & main 쓰레드 종료");
    }
}
