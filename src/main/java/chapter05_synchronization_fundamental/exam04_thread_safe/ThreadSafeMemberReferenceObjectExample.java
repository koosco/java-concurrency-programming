package chapter05_synchronization_fundamental.exam04_thread_safe;


public class ThreadSafeMemberReferenceObjectExample {
    public static void main(String[] args) throws InterruptedException {
//        new Thread(new MyRunnable(new Company("user"))).start();
//        new Thread(new MyRunnable(new Company("user"))).start();

        Thread.sleep(1000);
        System.out.println("################################################");

        Company company = new Company("user");
        new Thread(new MyRunnable(company)).start();
        new Thread(new MyRunnable(company)).start();

    }
}

class MyRunnable implements Runnable {

    private Company company;

    public MyRunnable(Company company) {
        // 매개변수로 원시 타입을 받는다면 복사본이 저장된다
        // 하지만 객체를 받는 경우 thread safe하지 못하다
        this.company = company;
    }

    @Override
    public void run() {
        company.changeName(Thread.currentThread().getName());
    }
}

class Company {

    private Member member;

    public Company(String name) {
        this.member = new Member(name);
    }

    public void changeName(String name) {
        String oldName = member.getName();
        member.setName(name);
        System.out.println(oldName + ": " + member.getName());

    }
}

class Member {
    private String name;

    public Member(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}