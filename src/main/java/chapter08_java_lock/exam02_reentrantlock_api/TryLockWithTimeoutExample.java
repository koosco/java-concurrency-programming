package chapter08_java_lock.exam02_reentrantlock_api;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockWithTimeoutExample {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            try {
                if (lock.tryLock(2, TimeUnit.SECONDS)) {
                    System.out.println("쓰레드 1이 락을 획득");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                    } finally {
                        lock.unlock();
                        System.out.println("쓰레드 1이 락을 해제");
                    }
                } else {
                    System.out.println("쓰레드 1이 락을 획득하지 못했습니다");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                if (lock.tryLock(4, TimeUnit.SECONDS)) {
                    System.out.println("쓰레드 2가 락을 획득");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                    } finally {
                        lock.unlock();
                        System.out.println("쓰레드 2가 락을 해제");
                    }
                } else {
                    System.out.println("쓰레드 2가 락을 획득하지 못했습니다");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();

    }
}
