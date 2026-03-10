package SS3.bai2;

import java.util.List;

record User(String username, String email, String status) {}

public class Main {
    public static void main(String[] args) {
        // 1. Tạo danh sách dữ liệu mẫu
        List<User> users = List.of(
            new User("alice", "alice@gmail.com", "ACTIVE"),
            new User("bob", "bob@yahoo.com", "INACTIVE"),
            new User("charlie", "charlie@gmail.com", "ACTIVE"),
            new User("dave", "dave@outlook.com", "ACTIVE")
        );

        System.out.println("--- Các username sử dụng Gmail ---");

        // 2. Sử dụng Stream để lọc và in kết quả
        users.stream()
            .filter(user -> user.email().endsWith("@gmail.com")) 
            .map(User::username)                              
            .forEach(System.out::println);                     
    }
}