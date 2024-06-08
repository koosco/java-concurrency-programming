package chapter04_java_thread_api_uses.exam05_threadlocal;

public class InheritableThreadLocalExample {

    public static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        inheritableThreadLocal.set("부모 쓰레드의 값");

        Thread childThread = new Thread(() -> {
            System.out.println("자식 쓰레드에서 부모로부터 상속받은 값: " + inheritableThreadLocal.get());
            inheritableThreadLocal.set("자식 쓰레드의 새로운 값");
            System.out.println("자식 쓰레드에서 설정한 후의 값 : " + inheritableThreadLocal.get());
        });
        childThread.start();

        childThread.join();

        System.out.println("자식 쓰레드에서 값을 설정한 후의 부모 쓰레드의 값 : " + inheritableThreadLocal.get());
    }
}
