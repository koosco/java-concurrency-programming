package chapter10_java_synchronization_framework.exam01_threadpool;

import java.util.LinkedList;
import java.util.Queue;

public class SimpleThreadPool {
    /**
     * 직접 쓰레드를 만들지 않고 쓰레드 풀을 통해 쓰레드를 받음
     */
    private int numThreads;
    private Queue<Runnable> taskQueue;
    private Thread[] threads; // 생성된 thread를 관리
    private volatile boolean isShutdown;

    public SimpleThreadPool(int numThreads) {
        this.numThreads = numThreads;
        taskQueue = new LinkedList<>();
        this.threads = new Thread[numThreads];
        this.isShutdown = false;

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new WorkerThread();
            threads[i].start();
        }
    }

    public void submit(Runnable task) {
        if (!isShutdown) {
            synchronized (taskQueue) {
                taskQueue.offer(task); // queue에 데이터가 가득 차거나 비어있다면 대기해야 함
                // linkedlist는 큐가 없는 경우만 처리해주면 됨
                taskQueue.notifyAll();
            }
        }
    }

    public void shutdown() {
        isShutdown = true;
        synchronized (taskQueue) {
            taskQueue.notifyAll();
        }

        for (Thread thread : threads) {
            try {
                thread.join(); // 모든 쓰레드가 종료될 때까지 대기
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (!isShutdown) {
                Runnable task;
                synchronized (taskQueue) {
                    while (taskQueue.isEmpty() && !isShutdown) {
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                if (!taskQueue.isEmpty()) {
                    task = taskQueue.poll();
                    task.run();
                } else {
                    continue;
                }
                task.run();
            }
        }
    }
}
