package chapter02_java_thread_structure.exam02_thread_start;

public class ThreadStartRunExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " : 쓰레드 실행중..");
            System.out.println("자식1 thread 종료");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " : 쓰레드 실행중..");
            System.out.println("자식2 thread 종료");
        });

        thread.start();
        thread2.start();
        System.out.println("main thread 종료");
    }
}
