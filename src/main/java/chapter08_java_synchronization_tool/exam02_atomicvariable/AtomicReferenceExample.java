package chapter08_java_synchronization_tool.exam02_atomicvariable;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceExample {

    public static void main(String[] args) throws InterruptedException {
        User user1 = new User("Alice", 25);
        User user2 = new User("Bob", 30);

        AtomicReference<User> atomicReference = new AtomicReference<>(user2);

        Thread thread1 = new Thread(() -> {
            User updateUser = new User("Carol", 40);
            boolean success = atomicReference.compareAndSet(user1, updateUser);
            if (success) {
                System.out.println("Thread1이 " + updateUser + "로 변경에 성공");
            } else {
                System.out.println("Thread1이 " + updateUser + "로 변경에 실패");
            }
        });

        Thread thread2 = new Thread(() -> {
            User updateUser = new User("Carol", 40);
            boolean success = atomicReference.compareAndSet(user2, updateUser);
            if (success) {
                System.out.println("Thread2가 " + updateUser + "로 변경에 성공");
            } else {
                System.out.println("Thread2가 " + updateUser + "로 변경에 실패");
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final user: " + atomicReference.get());
    }

    static class User {
        private final String name;
        private final int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
