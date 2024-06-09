package chapter05_synchronization_fundamental.exam01_singlethread_multithread;

public class SingleThreadExample {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int sum = 0;
        for (int i = 1; i <= 1000; i++) {
            sum += i;
            try {
                Thread.sleep(1);
//                throw new RuntimeException("error"); // 싱글 쓰레드에서 에러가 발생했을 때 전체 애플리케이션에 미치는 영향 -> 아예 정지됨
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("sum = " + sum);
        System.out.println("싱글 쓰레드 처리 시간: " + (System.currentTimeMillis() - start) + "ms");
    }
}
