package chapter08_java_lock.exam03_readwritelock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        SharedData sharedData = new SharedData();

        Thread reader1 = new Thread(() -> {
            readWriteLock.readLock().lock();
            try {
                System.out.println("읽기 쓰레드가 데이터를 읽고 있습니다");
                System.out.println("sharedData = " + sharedData.getData());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readWriteLock.readLock().unlock();
            }
        });

        Thread reader2 = new Thread(() -> {
            readWriteLock.readLock().lock();
            try {
                System.out.println("읽기 쓰레드가 데이터를 읽고 있습니다");
                System.out.println("sharedData = " + sharedData.getData());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readWriteLock.readLock().unlock();
            }
        });

        Thread writer = new Thread(() -> {
            readWriteLock.writeLock().lock();
            try {
                System.out.println("쓰기 쓰레드가 데이터를 읽고 있습니다");
                sharedData.setData(40);
                Thread.sleep(2000);
                System.out.println("쓰기 쓰레드가 데이터를 변경했습니다");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                readWriteLock.writeLock().unlock();
            }
        });

        writer.start();
        reader1.start();
        reader2.start();
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
 * 쓰기 thread가 수행되는 동안에는 읽기 thread가 접근할 수 없음
 * reader1이 lock을 획득했을 때도 reader2도 lock을 획득할 수 있음
 * 즉, 읽기 락은 락이 없는 것처럼 동작을 하고, 쓰기 락이 수행될 때만 mutex가 사용되는 것처럼 이해할 수 있다
 */