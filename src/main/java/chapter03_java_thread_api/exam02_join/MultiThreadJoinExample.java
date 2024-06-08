package chapter03_java_thread_api.exam02_join;

public class MultiThreadJoinExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                System.out.println("쓰레드1이 3초 동안 작동합니다");
                Thread.sleep(3000);
                System.out.println("쓰레드1 작동 완료");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                System.out.println("쓰레드2가 3초 동안 작동합니다");
                Thread.sleep(3000);
                System.out.println("쓰레드2 작동 완료");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("자식 쓰레드가 모두 종료된 후 부모 쓰레드 실행");
    }
}
