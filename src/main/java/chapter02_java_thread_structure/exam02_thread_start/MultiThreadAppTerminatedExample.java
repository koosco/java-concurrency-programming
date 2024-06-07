package chapter02_java_thread_structure.exam02_thread_start;

public class MultiThreadAppTerminatedExample {
    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new ThreadStackExample.MyRunnable(i));
            thread.start();
        }

        System.out.println("메인 쓰레드 종료");
    }

    static class MyRunnable implements Runnable {

        private final int threadId;

        public MyRunnable(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " : 쓰레드 실행중");
            firstMethod(threadId);
        }

        private void firstMethod(int threadId) {
            int localValue = threadId + 100;
            secondMethod(localValue);
        }

        private void secondMethod(int localValue) {
            String objectReference = threadId + ": Hello World";
            System.out.println(Thread.currentThread().getName() + ", 쓰레드 ID : " + threadId + ", Value : " + localValue);
        }
    }
}
