package SS2.bai6;

class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

@FunctionalInterface
interface UserProcessor {
    String process(User u);
}

class UserUtils {
    public static String convertToUpperCase(User u) {
        return u.getUsername().toUpperCase();
    }
}

public class Main {
    public static void main(String[] args) {
        User user = new User("nguyenvana");

        UserProcessor processor = UserUtils::convertToUpperCase;

        String result = processor.process(user);
        
        System.out.println("Tên người dùng gốc: " + user.getUsername());
        System.out.println("Kết quả sau khi process (UpperCase): " + result);
    }
}