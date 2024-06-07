package chapter02_java_thread_structure.exam02_thread_start;

public class ThreadStackExample {
    public static void main(String[] args) {

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new MyRunnable(i));
            thread.start();
        }
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

/**
 * 각 쓰레드의 stack마다 변수값들이 따로 존재하는 것을 확인할 수 있다
 * 참조값을 확인해보면 쓰레드마다 참조값이 다른 것을 확인할 수 있다
 */