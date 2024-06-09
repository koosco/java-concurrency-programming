package chapter06_synchronization_uses.exam01_mutex;

public class Mutex {

    private boolean lock = false;

    public synchronized void acquired() {
        // 여러 쓰레드들이 acquired라는 메서드에 접근할 수 있다
        // 이것을 막기 위해 synchronized 키워드를 사용
        while (lock) {
            try {
                wait(); // lock을 얻지 못하면 대기함
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.lock = true; // 처음 thread가 lock을 획득한 후 lock을 설정하여 다음 thread가 접근하지 못하도록 할 수 있음
    }

    public synchronized void release() {
        this.lock = false;
        this.notify(); // 대기하는 thread를 깨움
    }
}
