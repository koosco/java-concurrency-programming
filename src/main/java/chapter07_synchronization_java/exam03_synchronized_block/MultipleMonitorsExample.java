package chapter07_synchronization_java.exam03_synchronized_block;


class BankAccount {
    private double balance;
    private final Object lock = new Object();

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    /**
     * 입금
     */
    public void deposit(double amount) {
        synchronized (lock) {
            balance += amount;
        }
    }

    /**
     * 출금
     */
    public boolean withdraw(double amount) {
        synchronized (lock) {
            if (balance < amount) {
                return false;
            }
            balance -= amount;
            return true;
        }
    }

    /**
     * 계좌 이체
     */
    public boolean transfer(BankAccount to, double amount) {
        /**
         * thread가 transfer를 사용하면 thread는 현재 인스턴스(A 계좌)의 lock과
         * to 인스턴스(B계좌)의 lock을 동시에 갖고 있는 상태가 된다
         *
         * 즉 이 thread가 transfer 작업을 완료할 때까지는 다른 어떤 쓰레드도
         * A계좌와 B계좌 모두를 획득할 수 없다
         *
         * 두 계좌 모두 원자성이 보장되어야 한다. 하나의 계좌에서만 금액이 출금되고 한 쪽에서는 입금되지 않으면 안되기 때문
         * 출금과 입금은 동시에 이루어져야 한다
         */
        synchronized (this.lock) {
            if (this.withdraw(amount)) {
                synchronized (to.lock) { // 이체를 받을 대상의 락을 사용
                    to.deposit(amount);
                    return true;
                }
            }
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class MultipleMonitorsExample {
    public static void main(String[] args) throws InterruptedException {
        BankAccount accountA = new BankAccount(1000);
        BankAccount accountB = new BankAccount(1000);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                boolean result = accountA.transfer(accountB, 10);
                if (result) {
                    System.out.println("accountA에서 accountB로 " + i + "번째 10송금 성공");
                } else {
                    System.out.println("accountA에서 accountB로 " + i + "번째 10송금 실패");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                boolean result = accountB.transfer(accountA, 10);
                if (result) {
                    System.out.println("accountB에서 accountA로 " + i + "번째 10송금 성공");
                } else {
                    System.out.println("accountB에서 accountA로 " + i + "번째 10송금 실패");
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("A계좌 잔고: " + accountA.getBalance());
        System.out.println("B계좌 잔고: " + accountB.getBalance());
    }
}
