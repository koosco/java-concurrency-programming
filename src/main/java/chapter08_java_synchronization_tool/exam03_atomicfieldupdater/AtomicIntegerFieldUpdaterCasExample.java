package chapter08_java_synchronization_tool.exam03_atomicfieldupdater;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicIntegerFieldUpdaterCasExample {
    private static AtomicIntegerFieldUpdater<MyClass> fieldUpdater;
    private static int THREAD_COUNT = 3;

    public static void main(String[] args) throws InterruptedException {

        fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(MyClass.class, "counter");
        MyClass myClass = new MyClass();

        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100_000; j++) {
                    int expectedValue;
                    int newValue;
                    do {
                        expectedValue = fieldUpdater.get(myClass);
                        newValue = expectedValue + 1;
                    } while (!fieldUpdater.compareAndSet(myClass, expectedValue, newValue)); // 값이 다르다면 재시도를 수행
//                    value = newValue;

                    System.out.println(Thread.currentThread().getName() + ": " + expectedValue + ": " + newValue);
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i].join();
        }

        System.out.println("최종값 = " + fieldUpdater.get(myClass));
    }

    static class MyClass {
        private volatile int counter;

        public int getCounter() {
            return counter;
        }
    }
}
/**
 * 하나의 값만 원자성을 보장하고 싶으면 Atomic으로 만들 필요없이 바꾸고자하는 필드만 volatile로 선언하고
 * AtomicFieldUpdater로 원자성을 보장할 수 있다
 */