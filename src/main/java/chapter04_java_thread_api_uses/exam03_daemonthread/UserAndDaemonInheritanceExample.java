package chapter04_java_thread_api_uses.exam03_daemonthread;

public class UserAndDaemonInheritanceExample {
    public static void main(String[] args) throws InterruptedException {
        Thread userThread = new Thread(() -> {
            new Thread(() -> {
                System.out.println("사용자 쓰레드의 자식 쓰레드의 데몬 상태: " + Thread.currentThread().isDaemon());
            }).start();
            System.out.println("사용자 쓰레드의 데몬 상태: " + Thread.currentThread().isDaemon());
        });

        Thread daemonThread = new Thread(() -> {
            new Thread(() -> {
                System.out.println("데몬 쓰레드의 자식 쓰레드의 데몬 상태: " + Thread.currentThread().isDaemon());
            }).start();
            System.out.println("데몬 쓰레드의 데몬 상태: " + Thread.currentThread().isDaemon());
        });

        daemonThread.setDaemon(true);

        userThread.start();
        daemonThread.start();

        userThread.join();

        System.out.println("main thread 종료");
    }
}
