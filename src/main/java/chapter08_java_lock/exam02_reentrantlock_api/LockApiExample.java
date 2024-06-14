package chapter08_java_lock.exam02_reentrantlock_api;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockApiExample {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        Thread thread1 = new Thread(() -> {

        });

        Thread thread2 = new Thread(() -> {

        });

        System.out.println("lock.getHoldCount() = " + lock.getHoldCount());
        System.out.println("lock.hasQueuedThreads() = " + lock.hasQueuedThreads());
        System.out.println("lock.hasQueuedThread(thread1) = " + lock.hasQueuedThread(thread1));
        System.out.println("lock.hasQueuedThread(thread1) = " + lock.hasQueuedThread(thread2));
        System.out.println("lock.getQueueLength() = " + lock.getQueueLength());
        System.out.println("lock.isFair() = " + lock.isFair());
    }
}
/**
 * 테스트나 개발 환경에서만 사용하는 것이 권장됨
 * 배포 환경에서 사용하면 문제가 일어날 수 있음
 */