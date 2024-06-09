package chapter05_synchronization_fundamental.exam01_singlethread_multithread;

public class MultiThreadExceptionExample {

    private static int sum = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();

        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 500; i++) {
                synchronized (lock) {
                    sum += i;
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 501; i <= 1000; i++) {
                synchronized (lock) {
                    sum += i;
                }
                try {
                    Thread.sleep(1);
                    throw new RuntimeException("error");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println("sum = " + sum);
        System.out.println("멀티 쓰레드 처리 시간: " + (System.currentTimeMillis() - start) + "ms");
    }
}
