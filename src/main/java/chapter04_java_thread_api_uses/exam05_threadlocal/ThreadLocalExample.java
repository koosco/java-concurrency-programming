package chapter04_java_thread_api_uses.exam05_threadlocal;

public class ThreadLocalExample {

    //    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "Hello world");

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
            threadLocal.set("쓰레드 1의 값");
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
            threadLocal.remove();
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get()); // 값을 지우면 초기값으로 다시 설정된다
        }, "Thread-1").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
            threadLocal.set("쓰레드 2의 값");
            System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
            threadLocal.remove();
        }, "Thread-2").start();
    }
}
