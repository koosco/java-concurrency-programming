package chapter07_synchronization_java.exam06_deadlock.solve;

public class NonDeadlockObjectsExample2 {

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


    static class ResourceA {
        public void methodA(ResourceB resourceB) {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + ": methodA 발생");
            }
            resourceB.methodB2();
        }

        public synchronized void methodA2(ResourceB resourceB) {
            System.out.println(Thread.currentThread().getName() + ": methodA2 실행");
        }
    }

    static class ResourceB {
        public void methodB(ResourceA resourceA) {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + ": methodB 발생");
            }
            resourceA.methodA2(this);
        }

        public void methodB2() {
            System.out.println(Thread.currentThread().getName() + ": methodB2 실행");
        }
    }
}


