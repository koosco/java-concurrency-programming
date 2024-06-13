package chapter07_synchronization_java.exam03_synchronized_block;


class MethodBlock {
}

public class StaticBlockSynchronizedExample {
    private static int count = 0;

    public static void incrementBlockClass() {
        synchronized (StaticBlockSynchronizedExample.class) {
            count++;
            System.out.println(Thread.currentThread().getName() + "가 StaticBlockSynchronizedExample 의해 block 동기화함, count: " + count);
        }
    }

    public static void incrementBlockOtherClass() {
        synchronized (MethodBlock.class) {
            count++;
            System.out.println(Thread.currentThread().getName() + "가 MethodBlock 의해 block 동기화함, count: " + count);
        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        StaticBlockSynchronizedExample example = new StaticBlockSynchronizedExample();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                StaticBlockSynchronizedExample.incrementBlockClass();
            }
        }, "쓰레드 1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                StaticBlockSynchronizedExample.incrementBlockOtherClass();
            }
        }, "쓰레드 2");



        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("example.instanceGetCount() = " + example.getCount());
    }
}
/**
 * 서로 다른 class 모니터를 사용하기 때문에 동기화가 이루어지고 있지 않음
 */
