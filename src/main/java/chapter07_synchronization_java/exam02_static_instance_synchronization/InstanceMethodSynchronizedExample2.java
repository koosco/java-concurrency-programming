package chapter07_synchronization_java.exam02_static_instance_synchronization;

public class InstanceMethodSynchronizedExample2 {

    private int count = 0;
    public synchronized void increment() {
        count++;
        System.out.println(Thread.currentThread().getName() + "가 증가시켰습니다, 현재값: " + count);
    }

    public synchronized void decrement() {
        count--;
        System.out.println(Thread.currentThread().getName() + "가 감소시켰습니다, 현재값: " + count);
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        InstanceMethodSynchronizedExample2 counter = new InstanceMethodSynchronizedExample2();
        InstanceMethodSynchronizedExample2 counter2 = new InstanceMethodSynchronizedExample2();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                counter.increment();
                counter2.decrement();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) {
                counter.decrement();
                counter2.increment();
            }
        });

        long start = System.currentTimeMillis();

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();
        System.out.println("수행 시간: " + (end - start) + "ms");
        System.out.println("counter1 최종값: " + counter.getCount());
        System.out.println("counter2 최종값: " + counter2.getCount());
    }
}
