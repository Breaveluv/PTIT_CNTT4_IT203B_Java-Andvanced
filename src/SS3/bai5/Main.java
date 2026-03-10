package SS3.bai5;

import java.util.Comparator;
import java.util.List;

record User(String username, String email, String status) {}

public class Main {
    public static void main(String[] args) {
        List<User> users = List.of(
            new User("alexander", "alex@gmail.com", "ACTIVE"),
            new User("bob", "bob@yahoo.com", "INACTIVE"),
            new User("charlotte", "char@gmail.com", "ACTIVE"),
            new User("benjamin", "ben@gmail.com", "ACTIVE"),
            new User("alice", "alice@gmail.com", "ACTIVE"),
            new User("christopher", "chris@gmail.com", "ACTIVE")
        );

        System.out.println("--- Top 3 người dùng có username dài nhất ---");

        users.stream()
            .sorted(Comparator.comparingInt((User u) -> u.username().length()).reversed())
            .limit(3)
            .forEach(user -> System.out.println(user.username() + " (" + user.username().length() + " ký tự)"));
    }
}