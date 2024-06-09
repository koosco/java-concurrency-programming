package chapter06_synchronization_uses.exam02_semaphore;

public class SharedResource {
    private int sharedValue = 0;
    private CommonSemaphore commonSemaphore;

    public SharedResource(CommonSemaphore semaphore) {
        this.commonSemaphore = semaphore;
    }

    public void sum() {
        try {
            commonSemaphore.acquire();
            for (int i = 0; i < 10_000_000; i++) {
                sharedValue++;
            }
        } finally {
            commonSemaphore.release();
        }
    }

    public int getSum() {
        return sharedValue;
    }
}
