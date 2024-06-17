package chapter10_java_synchronization_framework.exam03_callable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Runnable runnableTask = () -> {
            System.out.println("Runnable 작업 수행중..");
            // 작업 실행
            System.out.println("Runnable 작업 완료..");
        };

        executorService.execute(runnableTask);
        executorService.shutdown();
    }
}

/**
 * execute를 이용해 runnable을 전달
 * 결과값을 받을 수 없음
 */