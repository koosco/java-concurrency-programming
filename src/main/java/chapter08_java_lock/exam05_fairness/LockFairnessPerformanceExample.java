package chapter08_java_lock.exam05_fairness;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockFairnessPerformanceExample {
    private static final int THREAD_COUNT = 20;
    private static final int ITERATIONS = 100_000;
    private static final Lock fairLock = new ReentrantLock(true);
    private static final Lock unfairLock = new ReentrantLock(false);

    public static void main(String[] args) {
        runTest("공정한 락", fairLock);
        runTest("비공정한 락", unfairLock);
    }

    private static void runTest(String lockType, Lock lock) {
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    lock.lock();
                    try {
                        // 자원에 대한 작업 수행
                    } finally {
                        lock.unlock();
                    }
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println(lockType + "의 실행 시간: " + elapsedTime + "ms");
    }
}

/**
 * THREAD_COUNT = 4
 * 공정한 락의 실행 시간: 881ms
 * 비공정한 락의 실행 시간: 17ms
 *
 * THREAD_COUNT = 20
 * 공정한 락의 실행 시간: 3920ms
 * 비공정한 락의 실행 시간: 52ms
 */