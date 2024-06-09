package chapter06_synchronization_uses.exam03_monitor;

public class MutualExclusionExample {

    private int counter = 0;

    public synchronized void increment() {
        counter++;
        System.out.println("쓰레드: " + Thread.currentThread().getName() + ", 카운터 : " + counter);
    }

    public static void main(String[] args) {
        MutualExclusionExample example = new MutualExclusionExample();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 50_000; i++) {
                example.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 50_000; i++) {
                example.increment();
            }
        });

        thread1.start();
        thread2.start();
    }
}
