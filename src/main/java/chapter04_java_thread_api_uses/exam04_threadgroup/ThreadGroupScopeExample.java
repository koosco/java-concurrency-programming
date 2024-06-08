package chapter04_java_thread_api_uses.exam04_threadgroup;

public class ThreadGroupScopeExample {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup topGroup = new ThreadGroup("최상위 쓰레드 그룹");
        ThreadGroup subGroup = new ThreadGroup(topGroup, "하위 쓰레드 그룹");

        Thread topGroupThread = new Thread(topGroup, () -> {
            System.out.println("1. 상위 그룹 쓰레드에서 하위 그룹의 최대 우선 순위 설정 변경 전 : " + subGroup.getMaxPriority());
            subGroup.setMaxPriority(7);
            System.out.println("2. 상위 그룹 쓰레드에서 하위 그룹의 최대 우선 순위 설정 변경 후 : " + subGroup.getMaxPriority());
        }, "상위 쓰레드 그룹");

        Thread subGroupThread = new Thread(subGroup, () -> {
            System.out.println("3. 하위 그룹 쓰레드에서 상위 그룹의 최대 우선 순위 설정 변경 전 : " + topGroup.getMaxPriority());
            topGroup.setMaxPriority(4); // 최상위 쓰레드 그룹에 설정된 값이 새로 생성되는 쓰레드들에 적용된다
            System.out.println("4. 하위 그룹 쓰레드에서 상위 그룹의 최대 우선 순위 설정 변경 후 : " + topGroup.getMaxPriority());
        }, "하위 쓰레드 그룹");

        topGroupThread.start();
        subGroupThread.start();

        topGroupThread.join();
        subGroupThread.join();

        System.out.println(topGroupThread.getName() + " : " + topGroupThread.getPriority());
        System.out.println(subGroupThread.getName() + " : " + subGroupThread.getPriority());

        Thread userThread1 = new Thread(topGroup, () -> {
        }, "유저 쓰레드 1");

        Thread userThread2 = new Thread(subGroup, () -> {
        }, "유저 쓰레드 2");

        userThread1.start();
        userThread2.start();

        userThread1.join();
        userThread2.join();

        System.out.println(userThread1.getName() + " : " + userThread1.getPriority());
        System.out.println(userThread2.getName() + " : " + userThread2.getPriority());
    }
}

/**
 * 1. 상위 그룹 쓰레드에서 하위 그룹의 최대 우선 순위 설정 변경 전 : 10
 * 2. 상위 그룹 쓰레드에서 하위 그룹의 최대 우선 순위 설정 변경 후 : 4
 * 3. 하위 그룹 쓰레드에서 상위 그룹의 최대 우선 순위 설정 변경 전 : 10
 * 4. 하위 그룹 쓰레드에서 상위 그룹의 최대 우선 순위 설정 변경 후 : 4
 *
 * 상위 쓰레드 그룹 : 5
 * 하위 쓰레드 그룹 : 5
 *
 * 유저 쓰레드 1 : 4
 * 유저 쓰레드 2 : 4
 *
 * 상속 관계를 가질 때는 최상위 쓰레드 그룹에 설정된 priority가 적용된다
 */