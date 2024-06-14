package chapter08_java_lock.exam02_reentrantlock_api;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptiblyExample {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                System.out.println("쓰레드 1 락을 획득");
                try {
                    Thread.sleep(3000);
                } finally {
                    lock.unlock();
                    System.out.println("쓰레드 1 락을 해제");
                }
            } catch (InterruptedException e) {
                System.out.println("쓰레드 1이 인터럽트를 받음");
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
                System.out.println("쓰레드 2 락을 획득");
                try {
                    Thread.sleep(3000);
                } finally {
                    lock.unlock();
                    System.out.println("쓰레드 2 락을 해제");
                }
            } catch (InterruptedException e) {
                System.out.println("쓰레드 2가 인터럽트를 받음");
            }
        });


        thread1.start();
        thread2.start();

        thread1.interrupt();
        thread2.interrupt();

        thread1.join();
        thread2.join();
    }
}
