package chapter04_java_thread_api_uses.exam01_uncaughtexceptionhandler;

public class ThreadExceptionExample {
    public static void main(String[] args) {
        // thread에서 예외가 발생해도 main thread에서 예외 처리를 할 수 없음
        try {
            new Thread(() -> {
                throw new RuntimeException("쓰레드 예외 발생");
            }).start();
        } catch (Exception e) {
            notify(e);
        }
    }

    private static void notify(Exception e) {
        System.out.println("관리자에게 알림" + e);
    }
}
