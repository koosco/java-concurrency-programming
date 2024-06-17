package chapter10_java_synchronization_framework.exam02_executor;

import java.util.concurrent.Executor;

public class SyncExecutorExample {
    public static void main(String[] args) {
        SyncExecutor syncExecutor = new SyncExecutor();
        syncExecutor.execute(() -> {
            System.out.println("동기 작업 1 수행중..");
            // 작업 수행
            System.out.println("동기 작업 1 완료..");
        });

        syncExecutor.execute(() -> {
            System.out.println("동기 작업 2 수행중..");
            // 작업 수행
            System.out.println("동기 작업 2 완료..");
        });
    }

    static class SyncExecutor implements Executor {

        @Override
        public void execute(Runnable command) {
            command.run(); // 동기적으로 수행하지만 실행의 주체가 Executor가 된다
        }
    }
}
