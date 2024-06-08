package chapter04_java_thread_api_uses.exam03_daemonthread;

public class DaemonThreadLifeCycleExample {
    public static void main(String[] args) throws InterruptedException {
        Thread userThread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("사용자 쓰레드 1 실행중 ..");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread daemonThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                    System.out.println("데몬 쓰레드 실행중..");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        daemonThread.setDaemon(true);

        userThread.start();
        daemonThread.start();

        userThread.join();

        System.out.println("모든 쓰레드가 종료되고 메인 쓰레드가 종료");
    }
}
