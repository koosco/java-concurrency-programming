package chapter07_synchronization_java.exam01_fundamental;

public class SynchronizedExample {

    private int instanceCount = 0;
    private static int staticCount = 0;


    public synchronized void instanceMethod() {
        // 인스턴스의 모니터
        instanceCount++;
        staticCount++; // static과 별도의 모니터를 이용하기 때문에 동시에 접근이 가능해짐 -> 동시성 문제에 노출됨
        System.out.println("인스턴스 메서드 동기화 : " + instanceCount);
    }

    public static synchronized void staticMethod() {
        // 클래스의 모니터
        staticCount++;
        System.out.println("정적 메서드 동기화 : " + staticCount);
    }

    public void instanceBlock() {
        // 인스턴스의 모니터
        synchronized (this) {
            instanceCount++;
            staticCount++;
            System.out.println("인스턴스 메서드 동기화 : " + instanceCount);
        }
    }

    public static void staticBlock() {
        // 클래스의 모니터
        synchronized (SynchronizedExample.class) {
            staticCount++;
            System.out.println("정적 메서드 동기화 : " + staticCount);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample examples = new SynchronizedExample();

        new Thread(examples::instanceMethod).start();
        new Thread(SynchronizedExample::staticMethod).start();
        new Thread(examples::instanceBlock).start();
        new Thread(SynchronizedExample::staticBlock).start();
    }
}
