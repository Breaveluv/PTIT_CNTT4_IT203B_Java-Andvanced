package SS4.bai2;

public class Main {
    public String registrationStatus = "";

    public void checkRegistrationAge(int age) {
        if (age < 0) {
            registrationStatus = "INVALID_AGE";
        } else if (age >= 18) {
            registrationStatus = "SUCCESS";
        } else {
            registrationStatus = "UNDER_AGE";
        }
    }
}