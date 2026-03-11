package SS4.bai5;

public class Main {

    public enum Role { ADMIN, MODERATOR, USER }
    public enum Action { DELETE_USER, LOCK_USER, VIEW_PROFILE }

    public static class User {
        public Role role;
        public User(Role role) { this.role = role; }
    }

  =
    public boolean canPerformAction(User user, Action action) {
        if (user == null || user.role == null) return false;

        switch (user.role) {
            case ADMIN:
                return true;
            case MODERATOR:
                return action == Action.LOCK_USER || action == Action.VIEW_PROFILE;
            case USER:
                return action == Action.VIEW_PROFILE;
            default:
                return false;
        }
    }
}