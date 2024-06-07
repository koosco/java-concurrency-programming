package chapter02_java_thread_structure.exam01_thread_creation;

public class ExtendThreadExample {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        myThread.start();
    }
}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " : 쓰레드 실행중..");
    }
}
