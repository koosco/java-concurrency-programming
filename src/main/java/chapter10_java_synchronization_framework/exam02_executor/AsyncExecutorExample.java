package chapter10_java_synchronization_framework.exam02_executor;

import chapter10_java_synchronization_framework.exam02_executor.SyncExecutorExample.SyncExecutor;
import java.util.concurrent.Executor;

public class AsyncExecutorExample {
    public static void main(String[] args) {
        /**
         * main thread에서는 runnable은 task만 정의하고 실행은 executor가 수행
         */
        AsyncExecutor asyncExecutor = new AsyncExecutor();
        asyncExecutor.execute(() -> {
            System.out.println("비동기 작업 1 수행중..");
            // 작업 수행
            System.out.println("비동기 작업 1 완료..");
        });

        asyncExecutor.execute(() -> {
            System.out.println("비동기 작업 2 수행중..");
            // 작업 수행
            System.out.println("비동기 작업 2 완료..");
        });
    }

    static class AsyncExecutor implements Executor {

        @Override
        public void execute(Runnable command) {
            Thread thread = new Thread(command);
            thread.start();
        }
    }
}
