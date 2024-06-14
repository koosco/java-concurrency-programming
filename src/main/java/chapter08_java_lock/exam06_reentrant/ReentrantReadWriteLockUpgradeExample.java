package chapter08_java_lock.exam06_reentrant;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockUpgradeExample {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void failedUpgradeAttempt() {
        System.out.println("읽기 잠금 획득 시도...");
        lock.readLock().lock();
        System.out.println("읽기 잠금 획득!");

        try {
            System.out.println("쓰기 잠금 획득 시도...");
            lock.writeLock().lock(); // 여기서 더 이상 진행되지 않음
            System.out.println("이 메시지는 출력되지 않음");
        }finally {
            lock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantReadWriteLockUpgradeExample example = new ReentrantReadWriteLockUpgradeExample();
        example.failedUpgradeAttempt();
    }
}
