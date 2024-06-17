package chapter09_java_synchronization_tool.exam01_cas;

import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadCasExample {
    private static AtomicInteger value = new AtomicInteger(0);
    private static int THREAD_COUNT = 3;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100_000; j++) {
                    int expectedValue;
                    int newValue;
                    do {
                        expectedValue = value.get();
                        newValue = expectedValue + 1;
                    } while (!value.compareAndSet(expectedValue, newValue)); // 값이 다르다면 재시도를 수행
//                    value = newValue;

                    System.out.println(Thread.currentThread().getName() + ": " + expectedValue + ": " + newValue);
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i].join();
        }

        System.out.println("최종값 = " + value.get());
    }
}
/**
 * cas는 lock이나 synchronized를 이용한 mutex를 이용하는 것이 아니라 cpu 원자성을 이용해 현재값과 기댓값을 비교하는 방법이다
 * <p>
 * 굉장히 많은 쓰레드가 실패를 반복한다면 성능이 안좋을 수 있음 -> 잠깐 대기했다고 돌아가면 됨 cas연산이 많아지게 되면 성능이 감소하게 됨
 */