package chapter08_java_lock.exam06_reentrant;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class ReentrantReadWriteLockDowngradeExample {
    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Lock readLock = lock.readLock();
        Lock writeLock = lock.writeLock();
        SharedData sharedData = new SharedData();

        new Thread(() -> {
            writeLock.lock();
            try {
                System.out.println("쓰기 락 획득: " + Thread.currentThread().getName());

                // 임계 영역 수행
                sharedData.setData((int) (Math.random()) * 100);
                System.out.println("데이터 업데이트");

                // 쓰기 락을 읽기 락으로 다운그레이드
                readLock.lock();
                System.out.println("다운그레이드: 쓰기 락 -> 읽기 락");

                // 쓰기 락 해제
                writeLock.unlock();
                System.out.println("쓰기 락 해제: " + Thread.currentThread().getName());

                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readLock.unlock();
                System.out.println("읽기 락 해제: " + Thread.currentThread().getName());
            }
        }).start();

        // 여러 개의 쓰레드가 읽기 락을 사용하는 경우
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                readLock.lock();
                try {
                    System.out.println("읽기 락 획득: " + Thread.currentThread().getName());
                    int data = sharedData.getData();
                    System.out.println(Thread.currentThread().getName() + ", 데이터 읽기" + data);
                } finally {
                    readLock.unlock();
                    System.out.println("읽기 락 해제: " + Thread.currentThread().getName());
                }
            }).start();
        }
    }

    static class SharedData {
        private int data = 0;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }
}
/**
 * 쓰기 락에서 읽기 락으로 변경한 이후에는 다른 읽기락을 갖는 쓰레드들도 함께 공유 데이터에 접근할 수 있게 됨
 */