package chapter01.exam01;

import java.util.ArrayList;

/**
 * cpu 개수만큼 task를 수행하면 cpu가 동시에 작업을 수행하기 때문에 500ms만큼의 수행 시간이 발생한다
 * cpu 개수 + 1 or cpu 개수 x 2만큼의 task를 주게 되면 500ms x 2만큼의 수행 시간이 걸리게 된다
 * 이것을 통해 parallelStream을 사용할 때 cpu 코어 수만큼의 task를 한 번에 수행함을 알 수 있다
 */
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