package chapter02_java_thread_structure.exam02_thread_start;

public class SingleThreadAppTerminatedExample {
    public static void main(String[] args) {
        int sum = 0;

        for (int i = 0; i < 1000; i++) {
            sum += i;
        }

        System.out.println("sum = " + sum);

        System.out.println("메인 쓰레드 종료");
    }
}
