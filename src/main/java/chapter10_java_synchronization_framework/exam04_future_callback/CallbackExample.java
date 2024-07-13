package chapter10_java_synchronization_framework.exam04_future_callback;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallbackExample {
    static interface Callback {
        void onComplete(int result);
    }

    static class MyCallback implements Callback {

        @Override
        public void onComplete(int result) {
            System.out.println("비동기 작업 처리 결과 : " + result);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            int result = 42;

            Callback callback = new MyCallback();
            callback.onComplete(result);
        });
        System.out.println("비동기 작업 시작");
        executorService.shutdown();
    }
}
