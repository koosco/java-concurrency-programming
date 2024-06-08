package chapter03_java_thread_api.exam04_name_currentthread_isalive;

public class ThreadNameExample {
    public static void main(String[] args) throws InterruptedException {
        // 1. 생성자 사용
        Thread thread1 = new Thread(() -> {
            System.out.println("현재 쓰레드 이름 : " + Thread.currentThread().getName());
        }, "koosThread");
        thread1.start();

        Thread thread2 = new Thread(() -> {
            System.out.println("현재 쓰레드 이름 : " + Thread.currentThread().getName());
        });
        thread2.setName("yourThread");
        thread2.start();

        for (int i = 0; i < 5; i++) {
            Thread defaultThread = new Thread(() -> {
                System.out.println("현재 쓰레드 이름 : " + Thread.currentThread().getName());
            });
            defaultThread.start();
        }

        Thread.sleep(2000);
    }
}

/**
 * 처음에 쓰레드를 하나 만들고 setName을 사용하면 실행하면 쓰레드 이름이 1부터 시작
 */