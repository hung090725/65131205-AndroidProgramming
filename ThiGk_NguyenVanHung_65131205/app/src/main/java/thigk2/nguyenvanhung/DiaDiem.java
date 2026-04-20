package thigk2.nguyenvanhung;

public class DiaDiem {
    private int hinhAnh;
    private String tenDiaDiem;
    private String diaChi;

    public DiaDiem(int hinhAnh, String tenDiaDiem, String diaChi) {
        this.hinhAnh = hinhAnh;
        this.tenDiaDiem = tenDiaDiem;
        this.diaChi = diaChi;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(int hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTenDiaDiem() {
        return tenDiaDiem;
    }

    public void setTenDiaDiem(String tenDiaDiem) {
        this.tenDiaDiem = tenDiaDiem;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
