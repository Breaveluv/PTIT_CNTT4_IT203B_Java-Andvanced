package SS4.bai6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private Main system;
    private Main.User currentUser;
    private List<Main.User> userList;

    @BeforeEach
    void setUp() {
        system = new Main();
        userList = new ArrayList<>();

        Main.UserProfile p1 = new Main.UserProfile(LocalDate.of(2000, 1, 1), "user1@gmail.com");
        currentUser = new Main.User(p1);

        Main.UserProfile p2 = new Main.UserProfile(LocalDate.of(1995, 5, 5), "existed@gmail.com");
        Main.User otherUser = new Main.User(p2);

        userList.add(currentUser);
        userList.add(otherUser);
    }

    @Test
    void testUpdateSuccess() {
        Main.UserProfile newProfile = new Main.UserProfile(LocalDate.of(2006, 8, 6), "new_mail@gmail.com");


        system.updateProfile(currentUser, newProfile, userList);

        assertEquals("UPDATE_SUCCESS", system.updateMessage, "Cập nhật phải thành công với dữ liệu đúng.");
    }

    @Test
    void testUpdateFutureDate() {
        LocalDate future = LocalDate.now().plusDays(10);
        Main.UserProfile newProfile = new Main.UserProfile(future, "user1@gmail.com");

        system.updateProfile(currentUser, newProfile, userList);

        assertEquals("INVALID_BIRTHDATE", system.updateMessage, "Phải báo lỗi khi chọn ngày sinh tương lai.");
    }

    @Test
    void testUpdateDuplicateEmail() {
        Main.UserProfile newProfile = new Main.UserProfile(LocalDate.of(2000, 1, 1), "existed@gmail.com");

        system.updateProfile(currentUser, newProfile, userList);

        assertEquals("EMAIL_ALREADY_EXISTS", system.updateMessage, "Phải báo lỗi khi email đã tồn tại.");
    }
}