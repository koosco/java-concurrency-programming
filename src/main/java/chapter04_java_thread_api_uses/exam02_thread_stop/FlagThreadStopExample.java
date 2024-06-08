package chapter04_java_thread_api_uses.exam02_thread_stop;

public class FlagThreadStopExample {

    //    private volatile static boolean running = true; // volatile 변수로 지정하면 memory에 running에 저장되게 된다
    private static boolean running = true;

    public static void main(String[] args) {

        new Thread(() -> {
            int count = 0;
            while (running) {
                try {
                    Thread.sleep(1);
                    /**
                     * 각각의 쓰레드마다 캐시 메모리를 갖고 있다
                     * 두 번째 thread에서 running=false로 설정하는 것은 cpu cache에 저장되게 된다
                     * 언제 이 값이 memory에 동기화될지는 알 수 없다
                     * running 값이 저장된 context는 thread들이 각각의 cpu cache에 저장하기 때문에 thread들이 저장하고 있는 running 값은 다르다
                     * 대기 상태에 빠지게 되면 context switching이 일어나게 된다
                     * context switching이 일어나게 되면 cache에 있는 값을 모두 비워주어야 한다
                     * cache는 두 번째 thread가 사용해야 하기 때문이다
                     * 다시 두 번째 thread에서 context switching이 일어나게 되면 cache에는 context가 저장되어 있지 않기 때문에
                     * memory로부터 context 정보를 가져와야 한다
                     */

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                count++;
            }
            System.out.println("쓰레드 1이 종료, count : " + count);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("쓰레드 2가 종료");
            running = false;
        }).start();

    }
}
