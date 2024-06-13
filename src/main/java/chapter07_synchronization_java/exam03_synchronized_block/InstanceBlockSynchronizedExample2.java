package chapter07_synchronization_java.exam03_synchronized_block;

public class InstanceBlockSynchronizedExample2 {
    private int count = 0;
    private Object lockObject = new Object();

    public void incrementBlockThis() {
        synchronized (this) {
            count++;
            System.out.println(Thread.currentThread().getName() + "가 this에 의해 block 동기화함, count: " + count);
        }
    }

    public void incrementBlockLock() {
        synchronized (lockObject) {
            count++;
            System.out.println(Thread.currentThread().getName() + "가 lockObject에 의해 block 동기화함, count: " + count);
        }
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) throws InterruptedException {
        InstanceBlockSynchronizedExample2 example = new InstanceBlockSynchronizedExample2();
        InstanceBlockSynchronizedExample2 example2 = new InstanceBlockSynchronizedExample2();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                example.incrementBlockThis();
            }
        }, "쓰레드 1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                example2.incrementBlockThis();
            }
        }, "쓰레드 2");

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                example.incrementBlockLock();
            }
        }, "쓰레드 3");

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                example2.incrementBlockLock();
            }
        }, "쓰레드 4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        System.out.println("example.instanceGetCount() = " + example.getCount());
        System.out.println("example2.instanceGetCount() = " + example2.getCount());
    }
}
