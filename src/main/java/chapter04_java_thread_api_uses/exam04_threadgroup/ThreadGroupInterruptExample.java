package chapter04_java_thread_api_uses.exam04_threadgroup;

public class ThreadGroupInterruptExample {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup topGroup = new ThreadGroup("최상위 쓰레드 그룹");
        ThreadGroup subGroup = new ThreadGroup(topGroup, "하위 쓰레드 그룹");

        Thread topGroupThread = new Thread(topGroup, () -> {
            while (true) {
                System.out.println("상위 쓰레드 그룹에서 실행");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "상위 그룹 쓰레드");

        Thread subGroupThread = new Thread(subGroup, () -> {
            while (true) {
                System.out.println("하위 쓰레드 그룹에서 실행");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "하위 그룹 쓰레드");

        topGroupThread.start();
        subGroupThread.start();

        Thread.sleep(3000);

        System.out.println("그룹 쓰레드를 중지");

        subGroupThread.interrupt();
        topGroupThread.interrupt();
    }
}
