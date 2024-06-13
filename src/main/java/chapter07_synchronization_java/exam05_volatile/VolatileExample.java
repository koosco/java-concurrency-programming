package chapter07_synchronization_java.exam05_volatile;

public class VolatileExample {

    volatile boolean running = true;

    public void volatileTest() {
        new Thread(() -> {
            int count = 0;
            while (running) {
                count++;
            }
            System.out.println("Thread 1 종료. count: " + count);
            // volatile을 사용하지 않으면 cpu cache에서 값을 가져오기 때문에
            // 두 번째 thread에서 값을 변경해도 첫 번째 thread는 값이 변경되지 않음
            // volatile을 적용하면 가시성이 확보되어 memory에 있는 값이 사용된다
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            System.out.println("Thread 2 종료중..");
            running = false;
        }).start();
    }

    public static void main(String[] args) {
        new VolatileExample().volatileTest();
    }
}
