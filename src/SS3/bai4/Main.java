package SS3.bai4;

import java.util.*;
import java.util.stream.Collectors;

record User(String username, String email, String status) {}

public class Main {
    public static void main(String[] args) {
        List<User> users = List.of(
            new User("alice", "alice@gmail.com", "ACTIVE"),
            new User("bob", "bob@yahoo.com", "INACTIVE"),
            new User("alice", "alice_new@gmail.com", "ACTIVE"),
            new User("charlie", "charlie@gmail.com", "ACTIVE"),
            new User("bob", "bob_work@pro.com", "ACTIVE")       
        );

        System.out.println("--- Danh sách gốc (có trùng lặp) ---");
        users.forEach(System.out::println);

        
        Collection<User> uniqueUsers = users.stream()
            .collect(Collectors.toMap(
                User::username, 
                user -> user, 
                (existing, replacement) -> existing 
            ))
            .values();

        System.out.println("\n--- Danh sách sau khi lọc trùng (theo Username) ---");
        uniqueUsers.forEach(System.out::println);
    }
}