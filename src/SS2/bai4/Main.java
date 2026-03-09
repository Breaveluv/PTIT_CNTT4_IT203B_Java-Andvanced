package SS2.bai4;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

class User {
    private String username;

    public User() {
        this.username = "Default_User";
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

public class Main {
    public static void main(String[] args) {
        Supplier<User> userSupplier = User::new;
        User newUser = userSupplier.get();

        List<User> users = new ArrayList<>();
        users.add(newUser);
        users.add(new User("admin_01"));
        users.add(new User("java_dev"));

        Function<User, String> getUsernameFunc = User::getUsername;

        Consumer<String> printer = System.out::println;

        System.out.println("--- Danh sách Username ---");
        
        users.stream()
             .map(getUsernameFunc) 
             .forEach(printer);      
    }
}