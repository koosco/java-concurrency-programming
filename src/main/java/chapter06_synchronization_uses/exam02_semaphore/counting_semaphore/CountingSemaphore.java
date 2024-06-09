package chapter06_synchronization_uses.exam02_semaphore.counting_semaphore;

import chapter06_synchronization_uses.exam02_semaphore.CommonSemaphore;

public class CountingSemaphore implements CommonSemaphore {

    private int signal;
    private int permits;

    public CountingSemaphore(int permits) {
        this.permits = permits;
        this.signal = permits;
    }

    @Override
    public synchronized void acquire() {
        while (signal == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.signal--;
        System.out.println(Thread.currentThread().getName() + " 락 획득, 현재 세마포어 값: " + signal);
    }

    @Override
    public synchronized void release() {
        if (this.signal < permits) {
            this.signal++;
            System.out.println(Thread.currentThread().getName() + " 락 획득, 현재 세마포어 값: " + signal);
            this.notify();
        }
    }
}
