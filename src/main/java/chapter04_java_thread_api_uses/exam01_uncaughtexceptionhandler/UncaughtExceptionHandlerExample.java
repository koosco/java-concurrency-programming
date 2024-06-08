package chapter04_java_thread_api_uses.exam01_uncaughtexceptionhandler;

import java.lang.Thread.UncaughtExceptionHandler;

public class UncaughtExceptionHandlerExample {
    public static void main(String[] args) {

        // thread1
        Thread thread1 = new Thread(() -> {
            System.out.println("쓰레드 1 시작!");

            throw new RuntimeException("예기치 않은 에러 발생");
        });

        thread1.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + "에서 예외 발생" + ", 예외 : " + e.getMessage());
            }
        });

        thread1.start();

        // thread2
        Thread thread2 = new Thread(() -> {
            System.out.println("쓰레드 2 시작!");

            throw new RuntimeException("예기치 않은 에러 발생");
        });

        thread2.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println(t.getName() + "에서 예외 발생" + ", 예외 : " + e.getMessage());
            }
        });

        thread2.start();
    }
}
