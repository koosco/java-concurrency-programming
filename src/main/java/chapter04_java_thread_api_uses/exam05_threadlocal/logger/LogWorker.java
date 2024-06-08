package chapter04_java_thread_api_uses.exam05_threadlocal.logger;

public class LogWorker implements Runnable{
    /**
     * 각 thread가 log를 남길 수 있도록 수행하는 runnable type의 클래스
     */
    @Override
    public void run() {
        ThreadLocalLogger.ServiceA serviceA = new ThreadLocalLogger.ServiceA();
        ThreadLocalLogger.ServiceB serviceB = new ThreadLocalLogger.ServiceB();
        ThreadLocalLogger.ServiceC serviceC = new ThreadLocalLogger.ServiceC();

        if (Thread.currentThread().getName().equals("Thread-1")) {
            serviceA.process();
            serviceB.process();
            serviceC.process();
        } else if (Thread.currentThread().getName().equals("Thread-2")) {
            serviceB.process();
            serviceA.process();
            serviceC.process();
        } else {
            serviceC.process();
            serviceA.process();
            serviceB.process();
        }

        ThreadLocalLogger.printLog();
        ThreadLocalLogger.clearLog();
    }
}
