package chapter06_synchronization_uses.exam01_mutex;

public class SharedData {

    private int sharedValue = 0;

    private Mutex mutex;

    public SharedData(Mutex mutex) {
        this.mutex = mutex;
    }

    public void sum() {
        // 여러 thread에서 sum을 호출하게 됨
        try{
            mutex.acquired();
            for (int i = 0; i < 100_000_000; i++) {
                sharedValue++;
            }
        } finally {
            mutex.release();
        }
    }

    public int getSum() {
        return sharedValue;
    }
}
