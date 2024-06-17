package chapter09_java_synchronization_tool.exam03_atomicfieldupdater;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class AtomicIntegerFieldUpdaterApiExample {

    private static AtomicIntegerFieldUpdater<MyClass> fieldUpdater1;
    private static AtomicReferenceFieldUpdater<MyClass, String> fieldUpdater2;

    public static void main(String[] args) {
        fieldUpdater1 = AtomicIntegerFieldUpdater.newUpdater(MyClass.class, "field1");
        fieldUpdater2 = AtomicReferenceFieldUpdater.newUpdater(MyClass.class, String.class, "field2");
        // 각각의 필드에 대해서 위와 같이 설정하면 원자성을 보장해준다 -> thread safety

        MyClass myClass = new MyClass();
        fieldUpdater1.addAndGet(myClass, 40);
        fieldUpdater2.compareAndSet(myClass, null, "myField");

        System.out.println("업데이트 결과: " + myClass.getField1());
        System.out.println("업데이트 결과: " + myClass.getField2());
    }

    static class MyClass {
        private volatile int field1; // 반드시 volatile 키워드를 사용해야 함
        private volatile String field2;
        // 기본적으로 volatile은 가시성은 보장해주지만 원자성은 보장해주지 못한다 -> thread safety하지 못함


        public int getField1() {
            return field1;
        }

        public String getField2() {
            return field2;
        }
    }
}
