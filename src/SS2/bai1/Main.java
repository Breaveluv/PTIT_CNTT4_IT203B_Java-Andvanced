package SS2.bai1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

class User {
    private String username;
    private String role;

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "User{username='" + username + "', role='" + role + "'}";
    }
}

public class Main {
    public static void main(String[] args) {

        Predicate<User> isAdmin = user -> "ADMIN".equalsIgnoreCase(user.getRole());

        Function<User, String> getUsernameFunc = user -> user.getUsername();

        Consumer<User> printDetail = user -> System.out.println("Thông tin: " + user);

        Supplier<User> defaultUserSupplier = () -> new User("new_member", "USER");


        List<User> userList = new ArrayList<>();

        userList.add(defaultUserSupplier.get());
        
        userList.add(new User("nguyenvana", "USER"));
        userList.add(new User("admin_boss", "ADMIN"));
        userList.add(new User("tranvanb", "USER"));

        System.out.println("=== HỆ THỐNG QUẢN LÝ USER ===");

        userList.forEach(user -> {
            printDetail.accept(user);

            if (isAdmin.test(user)) {
                String adminName = getUsernameFunc.apply(user);
                System.out.println("   => CẢNH BÁO: Phát hiện Admin hệ thống: " + adminName);
            }
        });
    }
}