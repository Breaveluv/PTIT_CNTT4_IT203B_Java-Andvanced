package SS1.bai5;

public class User {
    private String name;
    private int age;

    public void setAge(int age) {
        if (age < 0) {
            throw new InvalidAgeException("Lỗi đăng ký: Tuổi cung cấp (" + age + ") không được là số âm!");
        }
        if (age < 18) {
            throw new InvalidAgeException("Lỗi đăng ký: Người dùng phải từ 18 tuổi trở lên.");
        }
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}