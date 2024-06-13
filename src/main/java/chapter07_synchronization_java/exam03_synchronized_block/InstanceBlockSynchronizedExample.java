package chapter07_synchronization_java.exam03_synchronized_block;

import chapter07_synchronization_java.exam02_synchronized_method.InstanceStaticMethodSynchronizedExample;

public class InstanceBlockSynchronizedExample {

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
        InstanceBlockSynchronizedExample example = new InstanceBlockSynchronizedExample();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                example.incrementBlockThis();
            }
        }, "쓰레드 1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                example.incrementBlockThis();
            }
        }, "쓰레드 2");

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                example.incrementBlockLock();
            }
        }, "쓰레드 3");

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                example.incrementBlockLock();
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
    }
}

/**
 * 현재 모니터로 this와 lock을 사용하고 있음
 * 둘은 동시에 동기화가 되지 않기 때문에 동시성 제어가 처리되고 있지 않음
 * 4개의 쓰레드가 모두 동일한 모니터 대상을 사용해야 동시성 제어를 제대로 처리할 수 있음
 */