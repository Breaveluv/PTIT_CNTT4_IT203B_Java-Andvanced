package SS4.bai6;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static class UserProfile {
        public LocalDate birthDate;
        public String email;

        public UserProfile(LocalDate birthDate, String email) {
            this.birthDate = birthDate;
            this.email = email;
        }
    }

    public static class User {
        public UserProfile profile;
        public User(UserProfile profile) { this.profile = profile; }
    }

    // Biến lưu trữ trạng thái kết quả
    public String updateMessage = "";

    public void updateProfile(User existingUser, UserProfile newProfile, List<User> allUsers) {
        updateMessage = "";

        // Kiểm tra ngày sinh trong tương lai
        if (newProfile.birthDate != null && newProfile.birthDate.isAfter(LocalDate.now())) {
            updateMessage = "INVALID_BIRTHDATE";
            return;
        }

        // Kiểm tra email trùng
        for (User user : allUsers) {
            if (user != existingUser && user.profile.email.equalsIgnoreCase(newProfile.email)) {
                updateMessage = "EMAIL_ALREADY_EXISTS";
                return;
            }
        }

        // Cập nhật dữ liệu
        existingUser.profile = newProfile;
        updateMessage = "UPDATE_SUCCESS";
    }
}