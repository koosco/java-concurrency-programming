package chapter05_synchronization_fundamental.exam02_cpu_and_synchronization;

public class CpuNonSyncExample {

    private static int count = 0;
    private static final int ITERATIONS = 100000;

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                count++;
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < ITERATIONS; i++) {
                count++;
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("예상 결과 = " + ITERATIONS * 2);
        System.out.println("실제 결과 = " + count);
    }
}
