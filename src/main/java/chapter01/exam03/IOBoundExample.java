package chapter01.exam03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IOBoundExample {
    public static void main(String[] args) {
        int numThreads = Runtime.getRuntime().availableProcessors() / 2;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {
                try {

                    // IO 가 집중 되는 작업
                    for (int j = 0; j < 5; j++) {
                        Files.readAllLines(
                                Path.of("/Users/koo/Desktop/git/JavaConcurrencyProgramming/Java-Concurrency-Programming/src/main/java/chapter01/exam03/exampleFile"));
                        System.out.println("스레드: " + Thread.currentThread().getName() + ", "
                                + j); // IO Bound 일때 ContextSwitching 이 일어난다
                    }

                    // 아주 빠른 Cpu 연산
                    int result = 0;
                    for (long j = 0; j < 10; j++) {
                        result += j;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }
}

/**
 * cpu 연산에 비해 I/O 연산이 많이 일어나게 됨
 * 이런 경우 cpu가 대기하는 시간이 길기 때문에 대기하는 시간을 줄이고 다른 작업을 수행해야 한다
 * thread의 개수가 cpu 코어 개수와 동일하게 되면 I/O 작업을 할 때 작업을 수행하지 않게 된다
 */