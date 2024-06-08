package chapter03_java_thread_api.exam05_priority;

public class ThreadPriorityExample {
    public static void main(String[] args) throws InterruptedException {
        CountingThread minPriority = new CountingThread("min priority", Thread.MIN_PRIORITY);
        CountingThread normPriority = new CountingThread("norm priority", Thread.NORM_PRIORITY);
        CountingThread maxPriority = new CountingThread("max priority", Thread.MAX_PRIORITY);

        minPriority.start();
        normPriority.start();
        maxPriority.start();

        minPriority.join();
        normPriority.join();
        maxPriority.join();

        System.out.println("작업 완료");
    }

    static class CountingThread extends Thread {
        private final String threadName;
        private int count = 0;

        public CountingThread(String threadName, int priority) {
            this.threadName = threadName;
            setPriority(priority);
        }

        @Override
        public void run() {
            while (count < 10_000_000) {
                count++;
            }
            System.out.println(threadName + ": " + count);
        }
    }
}
