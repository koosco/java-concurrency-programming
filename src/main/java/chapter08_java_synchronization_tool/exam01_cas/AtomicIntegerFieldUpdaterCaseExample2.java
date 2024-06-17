package chapter08_java_synchronization_tool.exam01_cas;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicIntegerFieldUpdaterCaseExample2 {
    private final static AtomicReferenceFieldUpdater<AtomicIntegerFieldUpdaterCaseExample2, String> messageUpdater = AtomicReferenceFieldUpdater.newUpdater(
            AtomicIntegerFieldUpdaterCaseExample2.class, String.class, "message");

    private volatile String message = "";

    public void doSomething() {
        if (messageUpdater.compareAndSet(this, "", "Hello world")) {
            for (int i = 0; i < 10; i++) {
                System.out.println(messageUpdater.get(this));
            }
            messageUpdater.set(this, "");
        } else {
            System.out.println("현재 쓰레드는 들어오지 못합니다");
        }
    }

    public static void main(String[] args) {
        AtomicIntegerFieldUpdaterCaseExample2 example2 = new AtomicIntegerFieldUpdaterCaseExample2();
        new Thread(() -> {
            example2.doSomething();
        }).start();

        new Thread(() -> {
            example2.doSomething();
        }).start();
    }
}
