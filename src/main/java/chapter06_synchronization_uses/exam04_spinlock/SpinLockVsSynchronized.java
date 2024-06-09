package chapter06_synchronization_uses.exam04_spinlock;

import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLockVsSynchronized {
    private AtomicBoolean spinLock = new AtomicBoolean(false);
    private final Object syncLock = new Object();
    private int count = 0;

    final static int THREAD_COUNT = 50_000;
    final int ITERATIONS = 10_000_000;

    public void useSpinLock() {
        while(!spinLock.compareAndSet(false, true));
        for (int j = 0; j < ITERATIONS; j++) {
            count++;
        }
        spinLock.set(false);
    }

    public void useSynchronized() {
        synchronized (syncLock) {
            for (int j = 0; j < ITERATIONS; j++) {
                count++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLockVsSynchronized tester = new SpinLockVsSynchronized();

        // using spinLock
        Thread[] syncThreads = new Thread[THREAD_COUNT];
        long start1 = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            syncThreads[i] = new Thread(() -> {
                tester.useSpinLock();
            });
            syncThreads[i].start();
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            syncThreads[i].join();
        }
        long end1 = System.currentTimeMillis();
        System.out.println("spinLock 시간 : " + (end1 - start1));

        // using synchronized
        Thread[] syncThreads2 = new Thread[THREAD_COUNT];
        long start2 = System.currentTimeMillis();

        for (int i = 0; i < THREAD_COUNT; i++) {
            syncThreads2[i] = new Thread(() -> {
                tester.useSynchronized();
            });
            syncThreads2[i].start();
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            syncThreads2[i].join();
        }
        long end2 = System.currentTimeMillis();
        System.out.println("synchronized 시간 : " + (end2 - start2));
    }
}
