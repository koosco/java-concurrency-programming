package chapter08_java_synchronization_tool.exam02_atomicvariable;

public class NonAtomicIntegerGetAndUpdateExample {
    private static int accountBalance = 1000;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[5];
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> {
                int withdrawalAmount = 100; // 출금액
                int updatedBalance = 0;
                synchronized (NonAtomicIntegerGetAndUpdateExample.class) {
                    if (accountBalance >= withdrawalAmount) {
                        updatedBalance = accountBalance - withdrawalAmount;
                        accountBalance = updatedBalance;
                    }
                }

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
        System.out.println("synchronized 실행 시간: " + (end - start) + "ms");
    }
}
