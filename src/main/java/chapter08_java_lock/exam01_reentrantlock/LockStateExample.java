package chapter08_java_lock.exam01_reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockStateExample {

    private static final Lock lock =  new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("쓰레드가 1번 획득");
                lock.lock();
                try {
                    System.out.println("쓰레드가 2번 획득");
                } finally {
                    lock.unlock();
                    System.out.println("쓰레드가 1번 해제");
                }
            } finally {
                lock.unlock();
                System.out.println("쓰레드가 2번 해제");
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lock.lock();
            try {
                System.out.println("쓰레드가 2가 락 획득");
            }finally {
                lock.unlock();
                System.out.println("쓰레드가 2가 락 해제");
            }
        }).start();
    }
}
/**
 * 두 번째 thread는 첫 번째 thread가 모두 반납하기 전까지는 lock을 획득할 수 없음
 */