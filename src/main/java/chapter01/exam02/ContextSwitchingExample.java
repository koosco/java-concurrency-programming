package chapter01.exam02;

public class ContextSwitchingExample {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("!!!! Thread 1: " + i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("@@@@ Thread 2: " + i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println("#### Thread 3: " + i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}

/**
 * Output
 * @@@@ Thread 2: 0
 * #### Thread 3: 0
 * !!!! Thread 1: 0
 * @@@@ Thread 2: 1
 * #### Thread 3: 1
 * !!!! Thread 1: 1
 * @@@@ Thread 2: 2
 * #### Thread 3: 2
 * !!!! Thread 1: 2
 * #### Thread 3: 3
 * !!!! Thread 1: 3
 * @@@@ Thread 2: 3
 * #### Thread 3: 4
 * !!!! Thread 1: 4
 * @@@@ Thread 2: 4
 *
 * 유사하게 예제를 구현한 것이지 Context Switching 개념을 명확하게 설명하는 예제가 아님
 * 이렇게 순서대로 동작하는 것이 무작위로 실행되는 것이 아니라 CPU가 Scheduling을 받아 할당을 받아 출력을 하고 있는 것이다
 * cpu가 scheduling 방식에 따라 thread를 번갈아 가며 작업을 수행한다
 */