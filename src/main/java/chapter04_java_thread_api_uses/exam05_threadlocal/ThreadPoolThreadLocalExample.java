package chapter04_java_thread_api_uses.exam05_threadlocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolThreadLocalExample {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2); // 2개의 쓰레드를 가진 쓰레드 풀 생성

        executorService.submit(() -> {
            threadLocal.set("작업 1의 값");
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
            threadLocal.remove();
        });

        // 잠시 대기
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 두 번째 작업
        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
            });
        }

        executorService.shutdown();
    }
}

/**
 * 첫 번째 thread에 threadLocal값이 계속 유지되는 것을 볼 수 있다
 * pool-1-thread-1 : 작업 1의 값
 * pool-1-thread-2 : null
 * pool-1-thread-1 : 작업 1의 값
 * pool-1-thread-2 : null
 * pool-1-thread-1 : 작업 1의 값
 * pool-1-thread-2 : null
 */