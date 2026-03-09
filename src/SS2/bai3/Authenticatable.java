package SS2.bai3;

@FunctionalInterface
interface Authenticatable {
    String getPassword();

   
    default boolean isAuthenticated() {
        String password = getPassword();
        return password != null && !password.isEmpty();
    }

    static String encrypt(String rawPassword) {
        return "ENCRYPTED_" + rawPassword + "_SECURE"; 
    }
}