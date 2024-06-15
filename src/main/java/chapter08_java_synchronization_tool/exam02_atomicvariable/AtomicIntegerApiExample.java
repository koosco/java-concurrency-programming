package chapter08_java_synchronization_tool.exam02_atomicvariable;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

public class AtomicIntegerApiExample {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(10);
        int currentValue = atomicInteger.get();
        System.out.println("currentValue = " + currentValue); // 10

        atomicInteger.set(20);
        System.out.println("New value: " + atomicInteger.get()); // 20

        int previousValue = atomicInteger.getAndSet(30);
        System.out.println("previousValue = " + previousValue); // 20

        int newValue = atomicInteger.incrementAndGet();
        System.out.println("newValue = " + newValue); // 31

        boolean updated = atomicInteger.compareAndSet(31, 40);
        System.out.println("Update successful? " + updated);
        System.out.println("updated value = " + atomicInteger.get());

        IntUnaryOperator addFive = value -> value + 5;
        int previousValue2 = atomicInteger.getAndUpdate(addFive);
        System.out.println("previousValue2 = " + previousValue2);
        System.out.println("new value = " + atomicInteger.get());

    }
}
