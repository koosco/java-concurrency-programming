package chapter07_synchronization_java.exam06_deadlock.problem;

public class DeadlockObjectsExample {
    public static void main(String[] args) {
        ResourceA resourceA = new ResourceA();
        ResourceB resourceB = new ResourceB();

        Thread thread1 = new Thread(() -> {
            resourceA.methodA(resourceB);
        });

        Thread thread2 = new Thread(() -> {
            resourceB.methodB(resourceA);
        });

        thread1.start();
        thread2.start();
    }
}

class ResourceA {
    public synchronized void methodA(ResourceB resourceB) {
        System.out.println(Thread.currentThread().getName() + ": methodA 발생");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}
        resourceB.methodB2();
    }

    public synchronized void methodA2() {
        System.out.println(Thread.currentThread().getName() + ": methodA2 실행");
    }
}

class ResourceB {
    public synchronized void methodB(ResourceA resourceA) {
        System.out.println(Thread.currentThread().getName() + ": methodB 발생");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {}
            resourceA.methodA2();
    }

    public synchronized void methodB2() {
        System.out.println(Thread.currentThread().getName() + ": methodB2 실행");
    }
}
