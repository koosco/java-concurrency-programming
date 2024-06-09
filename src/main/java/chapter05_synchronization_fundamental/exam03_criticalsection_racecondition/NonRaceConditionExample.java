package chapter05_synchronization_fundamental.exam03_criticalsection_racecondition;

public class NonRaceConditionExample {

    private static int sharedResource = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread[] incrementThreads = new Thread[100];
        for (int i = 0; i < incrementThreads.length; i++) {
            incrementThreads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    synchronized (NonRaceConditionExample.class) {
                        sharedResource++;
                    }
                }
            });
            incrementThreads[i].start();
        }

        for (int i = 0; i < incrementThreads.length; i++) {
            incrementThreads[i].join();
        }

        System.out.println("Expected value = " + (100 * 10000));
        System.out.println("Actual value = " + sharedResource);
    }
}
