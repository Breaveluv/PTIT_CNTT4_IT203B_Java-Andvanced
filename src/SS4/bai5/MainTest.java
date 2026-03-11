package SS4.bai5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import SS4.bai5.Main.*;

class MainTest {

    private final Main system = new Main();
    private User testUser;

    @AfterEach
    void tearDown() {
        testUser = null;
    }

    @Test
    void testAdminPermissions() {
        testUser = new User(Role.ADMIN);
        assertAll("ADMIN phải có toàn quyền",
                () -> assertTrue(system.canPerformAction(testUser, Action.DELETE_USER)),
                () -> assertTrue(system.canPerformAction(testUser, Action.LOCK_USER)),
                () -> assertTrue(system.canPerformAction(testUser, Action.VIEW_PROFILE))
        );
    }

    @Test
    void testModeratorPermissions() {
        testUser = new User(Role.MODERATOR);
        assertAll("MODERATOR chỉ có quyền khóa và xem",
                () -> assertFalse(system.canPerformAction(testUser, Action.DELETE_USER)),
                () -> assertTrue(system.canPerformAction(testUser, Action.LOCK_USER)),
                () -> assertTrue(system.canPerformAction(testUser, Action.VIEW_PROFILE))
        );
    }

    @Test
    void testUserPermissions() {
        testUser = new User(Role.USER);
        assertAll("USER chỉ có quyền xem profile",
                () -> assertFalse(system.canPerformAction(testUser, Action.DELETE_USER)),
                () -> assertFalse(system.canPerformAction(testUser, Action.LOCK_USER)),
                () -> assertTrue(system.canPerformAction(testUser, Action.VIEW_PROFILE))
        );
    }
}