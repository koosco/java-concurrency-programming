package chapter10_java_synchronization_framework.exam03_callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Callable<Integer> callableTask = () -> {
            System.out.println("Callable 작업 수행중..");
            // 작업 실행
            System.out.println("Callable 작업 완료..");

            return 42;
        };

        Future<Integer> future = executorService.submit(callableTask);
        int result;
        try {
            result = future.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Callable 작업 결과 : " + result);
        executorService.shutdown();
    }
}
/**
 * submit을 이용해 callable을 전달
 * 결과값을 받을 수 있음
 */