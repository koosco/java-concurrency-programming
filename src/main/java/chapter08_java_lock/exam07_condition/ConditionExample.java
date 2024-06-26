package chapter08_java_lock.exam07_condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean flag = false;

    /**
     * 쓰레드 대기
     */
    public void awaiting() throws InterruptedException {
        lock.lock();
        try {
            while (!flag) {
                System.out.println("조건을 만족하지 못해 대기");
                condition.await();
            }
            System.out.println("임계 영역을 추가적으로 수행");
        }finally {
            lock.unlock();
        }
    }

    /**
     * 쓰레드 깨움
     */
    public void signaling() {
        lock.lock();
        try {
            flag = true;
            System.out.println("조건을 만족시키고 깨움");
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionExample conditionExample = new ConditionExample();

        Thread thread1 = new Thread(() -> {
            try {
                conditionExample.awaiting();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            conditionExample.signaling();
        });

        thread1.start();
        Thread.sleep(2000);
        thread2.start();


    }
}
/**
 * wait - notify와 기본적인 로직은 동일하지만
 * ReentrantLock의 Condition은 condition마다의 대기 큐를 갖기 때문에
 * 조건에 만족하는 쓰레드만 깨울 수 있고, 경쟁 상태를 완화시킬 수 있다
 */