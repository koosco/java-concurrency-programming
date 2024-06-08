package chapter03_java_thread_api.exam04_name_currentthread_isalive;

public class CurrentThreadExample {
    public static void main(String[] args) {
        System.out.println("현재 쓰레드 (main) : " + Thread.currentThread()); // native method
        System.out.println("현재 쓰레드 이름 : " + Thread.currentThread().getName()); // native method

        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("현재 쓰레드 이름 (thread) = " + getName());
            }
        };

        thread.start();

        Thread thread1 = new Thread(new ThreadName());
        thread1.start();
    }

    static class ThreadName implements Runnable {

        @Override
        public void run() {
            System.out.println("현재 쓰레드 (Runnable) : " + Thread.currentThread());
            System.out.println("현재 쓰레드 이름 : " + Thread.currentThread().getName());
        }
    }
}
