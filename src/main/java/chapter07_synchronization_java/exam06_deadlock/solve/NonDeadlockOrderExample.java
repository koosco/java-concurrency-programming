package chapter07_synchronization_java.exam06_deadlock.solve;

public class NonDeadlockOrderExample {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            process1();
        });

        Thread thread2 = new Thread(() -> {
            process2();
        });

        thread1.start();
        thread2.start();
    }

    private static void process1() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + "이 lock1을 획득했습니다");

            try {
                // thread 간의 경쟁 조건을 만들기 위해 잠시 대기
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "이 lock2를 획득했습니다");
            }
        }
    }

    private static void process2() {
        synchronized (lock1) {
            System.out.println(Thread.currentThread().getName() + "이 lock1을 획득했습니다");

            try {
                // thread 간의 경쟁 조건을 만들기 위해 잠시 대기
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock2) {
                System.out.println(Thread.currentThread().getName() + "이 lock2를 획득했습니다");
            }
        }
    }
}

/**
 * lock을 얻는 순서를 조정해 데드락을 피함
 * 이렇게 되면 순차적으로 처리해야 하기 때문에 성능적으로 손해를 볼 수 있음
 */