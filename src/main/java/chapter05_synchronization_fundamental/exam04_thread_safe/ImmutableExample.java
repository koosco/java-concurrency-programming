package chapter05_synchronization_fundamental.exam04_thread_safe;

public class ImmutableExample implements Runnable {
    private ImmutablePerson person;

    public ImmutableExample(ImmutablePerson person) {
        this.person = person;
    }

    @Override
    public void run() {
        System.out.println(
                Thread.currentThread().getName() + " - 이름 : " + person.getName() + ", 나이 : " + person.getAge());
    }

    public static void main(String[] args) {
        new ImmutablePerson("홍길동", 25);
    }
}

final class ImmutablePerson {

    private final String name;
    private final int age;

    public ImmutablePerson(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
