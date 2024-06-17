package chapter09_java_synchronization_tool.exam02_atomicvariable;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerGetAndUpdateExample {
    private static AtomicInteger accountBalance = new AtomicInteger(1_000);

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[5];
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                int withdrawalAmount = 100; // 출금액
                int updatedBalance = accountBalance.updateAndGet(balance -> {
                    if (balance >= withdrawalAmount) {
                        return balance - withdrawalAmount;
                    } else {
                        return balance;
                    }
                }); // 원자성을 보장하며 로직을 수행 가능

                if (updatedBalance <= 0) {
                    System.out.println("잔고 부족으로 출금 실패");
                } else {
                    System.out.println("출금 후 잔고: " + updatedBalance);
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long end = System.currentTimeMillis();
        System.out.println("Atomic 실행 시간: " + (end - start) + "ms");
    }
}
