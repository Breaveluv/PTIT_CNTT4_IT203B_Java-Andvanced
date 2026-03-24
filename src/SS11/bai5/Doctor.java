package SS11.bai5;

public class Doctor {
    private String doctorId;
    private String fullName;
    private String specialization;

    public Doctor() {}
    public Doctor(String id, String name, String spec) {
        this.doctorId = id;
        this.fullName = name;
        this.specialization = spec;
    }
    // Getters và Setters...
    public String getDoctorId() { return doctorId; }
    public String getFullName() { return fullName; }
    public String getSpecialization() { return specialization; }
}