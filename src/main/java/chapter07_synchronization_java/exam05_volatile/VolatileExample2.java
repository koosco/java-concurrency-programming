package chapter07_synchronization_java.exam05_volatile;

public class VolatileExample2 {
    private volatile int counter = 0;

    public void increment() {
        // 쓰기 쓰레드의 작업
        counter++;
    }

    public int getCounter() {
        // 읽기 쓰레드의 작업
        return counter;
    }

    public static void main(String[] args) {
        VolatileExample2 example = new VolatileExample2();


        // 쓰기 쓰레드
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                example.increment();
            }
            System.out.println("쓰기 쓰레드가 쓰기 작업을 맞췄습니다.");
        });

        // 읽기 쓰레드
        Runnable reader = () -> {
            int localValue = -1;
            while (localValue < 1000) {
                localValue = example.getCounter();
                System.out.println(Thread.currentThread().getName() + " 읽은 값: " + localValue);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        writer.start();
        for (int i = 0; i < 5; i++) {
            new Thread(reader).start();
        }
    }
}

/**
 * 하나의 쓰기 쓰레드와 다수의 읽기 쓰레드를 사용하는 경우 volatile을 사용하더라도
 * 동시성 문제가 발생하지 않음
 */
