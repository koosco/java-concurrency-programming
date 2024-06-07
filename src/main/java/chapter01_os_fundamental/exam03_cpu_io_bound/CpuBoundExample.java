package chapter01_os_fundamental.exam03_cpu_io_bound;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CpuBoundExample {
    public static void main(String[] args) {
        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        long startTime2 = System.currentTimeMillis();
        List<Future<?>> futures = new ArrayList<>(); // Future : thread 연산 결과가 저장됨

        for (int i = 0; i < numThreads; i++) {
            Future<?> future = executorService.submit(() -> {

                // Cpu 연산이 집중되고 오래 걸리는 작업
                // 주어진 cpu가 놀거나 대기 상태가 되면 안됨
                // 모든 thread가 cpu 코어를 사용할 수 있어야 함
                long result = 0;
                for (long j = 0; j < 1000000000L; j++) {
                    result += j;
                }

                // 아주 잠깐 대기함
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("스레드: " + Thread.currentThread().getName() + ", "
                        + result); // Cpu Bound 일때 ContextSwitching 은 크게 발생하지 않는다
            });
            futures.add(future);
        }
        futures.forEach(f -> {
            try {
                f.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
        long endTime2 = System.currentTimeMillis();
        System.out.println("CPU 개수를 초과하는 데이터를 병렬로 처리하는 데 걸린 시간: " + (endTime2 - startTime2) + "ms");
        executorService.shutdown();
    }
}

/**
 * 정확한 검증은 할 수 없지만 개념을 이해를 돕기 위한 예제 정도로 생각하면 됨
 */