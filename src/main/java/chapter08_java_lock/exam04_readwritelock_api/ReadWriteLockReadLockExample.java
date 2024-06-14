package chapter08_java_lock.exam04_readwritelock_api;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockReadLockExample {
    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();

        BankAccount account = new BankAccount(lock, 0);

        // 읽기 쓰레드가 잔액을 조회
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int balance = account.getBalance();
                System.out.println(Thread.currentThread().getName() + " - 현재 잔액: " + balance);
            }).start();
        }

        // 쓰기 쓰레드가 입금
        // 쓰기 작업을 할 때는 대기하지만 쓰기 작업이 끝나고 읽기 작업을 할 때는 병렬적으로 처리 가능
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                int depositAmount = (int) (Math.random() * 1000);
                account.deposit(depositAmount);
                System.out.println(Thread.currentThread().getName() + " - 입금: " + depositAmount);
            }).start();
        }

        // 읽기 쓰레드가 잔액을 조회
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int balance = account.getBalance();
                System.out.println(Thread.currentThread().getName() + " - 현재 잔액: " + balance);
            }).start();
        }
    }
}
