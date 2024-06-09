package chapter06_synchronization_uses.exam02_semaphore.binary_semaphore;

import chapter06_synchronization_uses.exam02_semaphore.SharedResource;

public class BinarySemaphoreExample {
    public static void main(String[] args) throws InterruptedException {

        SharedResource sharedResource = new SharedResource(new BinarySemaphore());

        Thread thread1 = new Thread(() -> {
            sharedResource.sum();
        });

        Thread thread2 = new Thread(() -> {
            sharedResource.sum();
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("sharedResource = " + sharedResource.getSum());
    }
}
