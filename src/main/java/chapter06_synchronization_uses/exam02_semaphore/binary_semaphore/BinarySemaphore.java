package chapter06_synchronization_uses.exam02_semaphore.binary_semaphore;

import chapter06_synchronization_uses.exam02_semaphore.CommonSemaphore;

public class BinarySemaphore implements CommonSemaphore {

    private int signal = 1;


    @Override
    public synchronized void acquire() {
        while (signal == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.signal = 0;
    }

    @Override
    public synchronized void release() {
        signal = 1;
        this.notify();
    }
}
