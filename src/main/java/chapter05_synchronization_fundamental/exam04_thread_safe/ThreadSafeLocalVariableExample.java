package chapter05_synchronization_fundamental.exam04_thread_safe;

public class ThreadSafeLocalVariableExample {

    int localSum = 0; // 공유된 변수를 사용하게 된다면 race condition이 발생하게 된다

    public void printNumbers(int plus) {
        /**
         * stack에 한정해서 로직을 작성하기 때문에 thread safe하다
         */
//        int localSum = 0;

        for (int i = 1; i <= 5; i++) {
            localSum += i;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        localSum += plus;
        System.out.println(Thread.currentThread().getName() + " - 현재 합계 : " + localSum);
    }

    public static void main(String[] args) {
        ThreadSafeLocalVariableExample example = new ThreadSafeLocalVariableExample();

        Thread thread1 = new Thread(() -> {
            example.printNumbers(50);
        }, "Thread-1");

        Thread thread2 = new Thread(() -> {
            example.printNumbers(40);
        }, "Thread-2");

        thread1.start();
        thread2.start();
    }
}
