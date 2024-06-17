package chapter09_java_synchronization_tool.exam01_cas;

public class NonAtomicExample {

    private static int value = 0;
    private static int THREAD_COUNT = 3;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100_000; j++) {
                    int expectedValue = value; // value를 임시적으로 저장
                    int newValue = expectedValue + 1; // 연산을 수행
                    value = newValue;
                    System.out.println(Thread.currentThread().getName() + ": " + expectedValue + ": " + newValue);
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i].join();
        }

        System.out.println("최종값 = " + value);
    }
}
