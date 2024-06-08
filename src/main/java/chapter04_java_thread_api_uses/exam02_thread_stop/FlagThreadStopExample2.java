package chapter04_java_thread_api_uses.exam02_thread_stop;

import java.util.concurrent.atomic.AtomicBoolean;

public class FlagThreadStopExample2 {

//    boolean running = true;
    private AtomicBoolean running = new AtomicBoolean(true);
    // cpu cache가 아니라 memory에서 공유 데이터를 값을 읽어올 수 있게 보장해줌

    public static void main(String[] args) {
        new FlagThreadStopExample2().flagTest();
    }

    private void flagTest() {
        new Thread(() -> {
            int count = 0;
            while (running.get()) {
                count++;
            }
            System.out.println("Thread1 종료, count: " + count);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            System.out.println("Thread2 종료..");
            running.set(false);
        }).start();
    }
}
