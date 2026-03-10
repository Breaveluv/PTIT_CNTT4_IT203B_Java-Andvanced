package SS3.bai1;

import java.util.List;

record User(String username, String email, String status) {}

public class Main {
    public static void main(String[] args) {
        // 2. Tạo danh sách 
        List<User> users = List.of(
            new User("alice", "alice@example.com", "ACTIVE"),
            new User("bob", "bob@example.com", "INACTIVE"),
            new User("charlie", "charlie@example.com", "ACTIVE")
        );

        // 3. In danh sách bằng users.forEach(...)
        System.out.println("--- Danh sách người dùng ---");
        users.forEach(user -> System.out.println(user));
    }
}