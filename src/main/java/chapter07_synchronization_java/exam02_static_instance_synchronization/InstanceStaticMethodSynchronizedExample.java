package chapter07_synchronization_java.exam02_static_instance_synchronization;

public class InstanceStaticMethodSynchronizedExample {

    private int instanceCount = 0;
    public synchronized void instanceIncrement() {
        instanceCount++;
        System.out.println(Thread.currentThread().getName() + "가 증가시켰습니다, 현재값: " + instanceCount);
    }

    public synchronized void instanceDecrement() {
        instanceCount--;
        System.out.println(Thread.currentThread().getName() + "가 감소시켰습니다, 현재값: " + instanceCount);
    }

    public int instanceGetCount() {
        return instanceCount;
    }


    private static int staticCount = 0;
    public static synchronized void increment() {
        staticCount++;
        System.out.println(Thread.currentThread().getName() + "가 증가시켰습니다, 현재값: " + staticCount);
    }

    public static synchronized void decrement() {
        staticCount--;
        System.out.println(Thread.currentThread().getName() + "가 감소시켰습니다, 현재값: " + staticCount);
    }

    public static int getCount() {
        return staticCount;
    }
    public static void main(String[] args) throws InterruptedException {
        InstanceStaticMethodSynchronizedExample example = new InstanceStaticMethodSynchronizedExample();

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                example.instanceDecrement();
            }
        }, "쓰레드 1");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                example.instanceIncrement();
            }
        }, "쓰레드 2");

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                InstanceStaticMethodSynchronizedExample.increment();
            }
        }, "쓰레드 3");

        Thread thread4 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                InstanceStaticMethodSynchronizedExample.decrement();
            }
        }, "쓰레드 4");

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        System.out.println("InstanceStaticMethodSynchronizedExample.getCount() = "
                + InstanceStaticMethodSynchronizedExample.getCount());
        System.out.println("example.instanceGetCount() = " + example.instanceGetCount());
    }
}
