package chapter02_java_thread_structure.exam01_thread_creation;

public class ImplementRunnable {
    public static void main(String[] args) {
        MyRunnable task = new MyRunnable();
        Thread thread = new Thread(task);
        thread.start();
    }
}

class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : 쓰레드 실행중..");
    }
}
