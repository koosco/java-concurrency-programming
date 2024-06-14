package chapter07_synchronization_java.exam06_deadlock.solve;

public class NonDeadlockDynamicOrderExample {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            methodWithLocks(lock1, lock2);
        });

        Thread thread2 = new Thread(() -> {
            methodWithLocks(lock2, lock1);
        });

        thread1.start();
        thread2.start();
    }

    private static void methodWithLocks(Object lockA, Object lockB) {
        /**
         * Lock의 순서를 변경 -> 데드락을 피함
         */
        Object firstLock = lockA;
        Object secondLock = lockB;

        if (System.identityHashCode(lockA) > System.identityHashCode(lockB)) {
            // 객체의 hashcode를 사용해 순서를 변경함
            firstLock = lockB;
            secondLock = lockA;
        }

        synchronized (firstLock) {
            System.out.println(Thread.currentThread().getName() + ": " + lockA + " 획득");

            synchronized (secondLock) {
                System.out.println(Thread.currentThread().getName() + ": " + lockB + " 획득");
            }
        }

    }
}
