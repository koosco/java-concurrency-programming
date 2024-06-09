package chapter05_synchronization_fundamental.exam04_thread_safe;

public class ThreadSafeLocalReferenceObjectExample {

//    LocalObject localObject = new LocalObject(); // thread에 안전하지 못하고 공유를 해버리게 된다

    class LocalObject {
        private int value;

        public void increment() {
            value++;
        }

        @Override
        public String toString() {
            return "LocalObject {" + value + "}";
        }
    }

    public void useLocalObject() {
        LocalObject localObject = new LocalObject(); // 쓰레드마다 객체를 생성하게 된다 -> heap에 서로 다른 객체들이 저장되게 됨
        for (int i = 0; i < 5; i++) {
            localObject.increment();
            System.out.println(Thread.currentThread().getName() + " - " + localObject);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ThreadSafeLocalReferenceObjectExample example = new ThreadSafeLocalReferenceObjectExample();

        Thread thread1 = new Thread(() -> {
            example.useLocalObject();
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            example.useLocalObject();
        }, "Thread-2");

        thread1.start();
        thread2.start();
    }
}
