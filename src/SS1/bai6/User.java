package SS1.bai6;

public class User {
    private String name;
    private int age;

    public void setAge(int age) {
        if (age < 0) {
            throw new InvalidAgeException("Tuổi không được là số âm: " + age);
        }
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // Tư duy phòng ngừa: Kiểm tra null trước khi xử lý chuỗi
    public void displayNameUpperCase() {
        if (this.name != null) {
            System.out.println("Tên người dùng: " + this.name.toUpperCase());
        } else {
            System.out.println("Tên người dùng: [Chưa xác định]");
        }
    }
}