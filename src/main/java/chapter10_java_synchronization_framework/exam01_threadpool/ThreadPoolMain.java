package chapter10_java_synchronization_framework.exam01_threadpool;

public class ThreadPoolMain {
    public static void main(String[] args) throws InterruptedException {
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool(3);
        for (int i = 0; i < 10; i++) {
            int taskId = i;
            simpleThreadPool.submit(() -> {
                System.out.println(Thread.currentThread().getName() + "작업 " + taskId + " 수행중..");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "작업 " + taskId + " 완료..");
            });
        }
        Thread.sleep(10000);
        simpleThreadPool.shutdown();
    }
}
