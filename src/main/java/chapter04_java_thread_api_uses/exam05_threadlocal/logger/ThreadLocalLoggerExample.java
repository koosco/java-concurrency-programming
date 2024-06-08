package chapter04_java_thread_api_uses.exam05_threadlocal.logger;

public class ThreadLocalLoggerExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new LogWorker());
        Thread thread2 = new Thread(new LogWorker());
        Thread thread3 = new Thread(new LogWorker());

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

    }
}

/**
 * [Thread-1]ServiceA 로직 수행->ServiceB 로직 수행->ServiceC 로직 수행
 * [Thread-2]ServiceB 로직 수행->ServiceA 로직 수행->ServiceC 로직 수행
 * [Thread-0]ServiceC 로직 수행->ServiceA 로직 수행->ServiceB 로직 수행
 */