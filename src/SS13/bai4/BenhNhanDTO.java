package SS13.bai4;

import java.util.List;

public class BenhNhanDTO {
    private int id;
    private String tenBenhNhan;
    private int tuoi;
    private List<DichVu> dsDichVu;

    public BenhNhanDTO() {}

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTenBenhNhan() { return tenBenhNhan; }
    public void setTenBenhNhan(String tenBenhNhan) { this.tenBenhNhan = tenBenhNhan; }
    public int getTuoi() { return tuoi; }
    public void setTuoi(int tuoi) { this.tuoi = tuoi; }
    public List<DichVu> getDsDichVu() { return dsDichVu; }
    public void setDsDichVu(List<DichVu> dsDichVu) { this.dsDichVu = dsDichVu; }
}