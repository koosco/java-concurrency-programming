package chapter04_java_thread_api_uses.exam01_uncaughtexceptionhandler;

import java.lang.Thread.UncaughtExceptionHandler;

public class DefaultExceptionHandlerExample {
    public static void main(String[] args) {

        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + "에서 예외 발생" + ", 예외 : " + e.getMessage());
            }
        });

        Thread thread1 = new Thread(() -> {
            throw new RuntimeException("스레드 1 예외!");
        });

        Thread thread2 = new Thread(() -> {
            throw new RuntimeException("스레드 2 예외!");
        });

        thread1.start();
        thread2.start();
    }
}
