package chapter06_synchronization_uses.exam02_semaphore.counting_semaphore;

import chapter06_synchronization_uses.exam02_semaphore.CommonSemaphore;
import chapter06_synchronization_uses.exam02_semaphore.SharedResource;

public class CountingSemaphoreExample {
    public static void main(String[] args) throws InterruptedException {
        int permits = 3;

        CommonSemaphore semaphore = new CountingSemaphore(permits);
        SharedResource sharedResource = new SharedResource(semaphore);

        int threadCount = 15;

        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(() -> {
                synchronized (CountingSemaphoreExample.class) {
                    sharedResource.sum(); // semaphore를 이용하면 상호 배제가 이루어지지 않기 때문에 상호 배제 처리를 해주어야 한다
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < threadCount; i++) {
            threads[i].join();
        }

        System.out.println("최종 값: " + sharedResource.getSum());
    }
}
