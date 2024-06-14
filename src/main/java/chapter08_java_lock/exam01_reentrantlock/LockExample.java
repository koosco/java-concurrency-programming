package chapter08_java_lock.exam01_reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample {

    private int count = 0;
    private Lock lock = new ReentrantLock();
    // 기본적으로 불공정락이지만, true를 주면 공정성 락으로 설정 가능

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        // thread가 쓰기 작업을 하는 동안에는 읽기 작업을 못할 수도 있음
        lock.lock();
        try {
            return count;
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        LockExample example = new LockExample();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                example.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10_000; i++) {
                example.increment();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("count: = " + example.getCount());
    }
}
