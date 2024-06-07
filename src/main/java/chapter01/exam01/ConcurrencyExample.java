package chapter01.exam01;

import java.util.ArrayList;

public class ConcurrencyExample {
    public static void main(String[] args) {
        int cpuCores = Runtime.getRuntime().availableProcessors();
        usingParallelStream(cpuCores);
        System.out.println();
        withOutParallelStream(cpuCores);
    }

    private static void usingParallelStream(int cpuCores) {
        System.out.println("cpuCores = " + cpuCores);

        ArrayList<Integer> data = new ArrayList<>();

        for (int i = 0; i < cpuCores; i++) {
            data.add(i);
        }

        long startTime = System.currentTimeMillis();

        long sum1 = data.parallelStream()
                .mapToLong(i -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i * i;
                })
                .sum();
        long endTime = System.currentTimeMillis();

        System.out.println("소요 시간: " + (endTime - startTime) + "ms");
        System.out.println("sum1 = " + sum1);
    }

    private static void withOutParallelStream(int cpuCores) {
        System.out.println("cpuCores = " + cpuCores);

        ArrayList<Integer> data = new ArrayList<>();

        for (int i = 0; i < cpuCores; i++) {
            data.add(i);
        }

        long startTime = System.currentTimeMillis();

        long sum1 = data.stream()
                .mapToLong(i -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i * i;
                })
                .sum();
        long endTime = System.currentTimeMillis();

        System.out.println("소요 시간: " + (endTime - startTime) + "ms");
        System.out.println("sum1 = " + sum1);
    }
}

/**
 * @ToDo result1
 * cpuCores = 8
 * 소요 시간: 517ms
 * 소요 시간: 4015ms
 *
 * @ToDo result2
 * cpuCores = 16
 * 소요 시간: 1017ms
 * 소요 시간: 8034ms
 *
 * @ToDo result3
 * cpuCores = 9
 * 소요 시간: 1009ms
 * 소요 시간: 4529ms
 */