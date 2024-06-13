package chapter07_synchronization_java.exam02_synchronized_method;

public class StaticMethodSynchronizedExample {

    private static int count = 0;
    public static synchronized void increment() {
        count++;
        System.out.println(Thread.currentThread().getName() + "가 증가시켰습니다, 현재값: " + count);
    }

    public static synchronized void decrement() {
        count--;
        System.out.println(Thread.currentThread().getName() + "가 감소시켰습니다, 현재값: " + count);
    }

    public static int getCount() {
        return count;
    }

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                StaticMethodSynchronizedExample.increment();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                StaticMethodSynchronizedExample.decrement();
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("최종값: " + StaticMethodSynchronizedExample.getCount());
    }
}

/**
 * static을 이용하면 인스턴스가 아닌 클래스를 이용하기 때문에 하나의 모니터만을 사용 가능
 */