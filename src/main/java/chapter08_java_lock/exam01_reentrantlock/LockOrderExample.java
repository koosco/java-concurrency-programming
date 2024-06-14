package chapter08_java_lock.exam01_reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockOrderExample {

   private static final Lock lock1 =  new ReentrantLock();
   private static final Lock lock2 =  new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            lock1.lock();
            try {
                System.out.println("쓰레드가 1번 락을 획득");
                lock2.lock();
                try {
                    System.out.println("쓰레드가 2번 락을 획득");
                }finally {
                    lock1.unlock();
                    System.out.println("쓰레드가 1번 락을 해제");
                }
            }finally {
                lock2.unlock();
                System.out.println("쓰레드가 2번 락을 해제");
            }
        }).start();
    }

    /**
     * synchronized 안에서 시작한 synchronized는 첫 번째 lock을 해제하기 전에 해제해야 한다
     */
//    public void method() {
//        synchronized (this) {
//            System.out.println("수행중..");
//            synchronized (this) {
//                System.out.println("수행중..");
//            }
//        }
//    }
}
