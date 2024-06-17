package chapter09_java_synchronization_tool.exam02_atomicvariable;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

public class AtomicReferenceApiExample {
    public static void main(String[] args) {
        AtomicReference<String> reference = new AtomicReference<>("Initial Value");
        String currentValue = reference.get();
        System.out.println("currentValue = " + currentValue);

        reference.set("New value");
        System.out.println("New value = " + reference.get());

        boolean success = reference.compareAndSet("New value", "Updated Value");
        System.out.println("Update success? " + success);
        System.out.println("currentValue = " + reference.get());

        String previousValue = reference.getAndSet("Final Value");
        System.out.println("previousValue = " + previousValue);
        System.out.println("currentValue = " + reference.get());

        UnaryOperator<String> operator = oldValue -> oldValue + " is correct";
        String newValue = reference.getAndUpdate(operator);
        System.out.println("newValue = " + newValue);
        System.out.println("current value = " + reference.get());
    }
}
