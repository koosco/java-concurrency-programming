package chapter08_java_lock.exam04_readwritelock_api;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockWriteLockExample {
    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();

        BankAccount account = new BankAccount(lock, 10_000);

        // 읽기 쓰레드가 잔액을 조회
        new Thread(() -> {
            int balance = account.getBalance();
            System.out.println(Thread.currentThread().getName() + " - 현재 잔액: " + balance);
        }).start();

        // 쓰기 쓰레드가 출금
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int withAmount = (int) (Math.random() * 1000);
                account.withdraw(withAmount);
                System.out.println(Thread.currentThread().getName() + " - 출금: " + withAmount);
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
