package SS3.bai3;

import java.util.List;
import java.util.Optional;

record User(String username, String email, String status) {}

class UserRepository {
    private final List<User> users = List.of(
        new User("alice", "alice@gmail.com", "ACTIVE"),
        new User("bob", "bob@yahoo.com", "INACTIVE"),
        new User("charlie", "charlie@gmail.com", "ACTIVE")
    );

    public Optional<User> findUserByUsername(String username) {
        return users.stream()
            .filter(u -> u.username().equalsIgnoreCase(username))
            .findFirst();
    }
}

public class Main {
    public static void main(String[] args) {
        UserRepository repository = new UserRepository();

        String searchName = "alice";
        Optional<User> userOptional = repository.findUserByUsername(searchName);

        userOptional.ifPresentOrElse(
            user -> System.out.println("Welcome " + user.username()),
            () -> System.out.println("Guest login")
        );
        
        System.out.print("Tìm kiếm 'unknown': ");
        repository.findUserByUsername("unknown").ifPresentOrElse(
            u -> System.out.println("Welcome " + u.username()),
            () -> System.out.println("Guest login")
        );
    }
}