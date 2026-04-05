package hung.edu.chbaithi;

/**
 * Model class: Lưu trữ thông tin của một bài thuốc
 */
public class BaiThuoc {
    private String tenBaiThuoc;   // Tên bài thuốc
    private String congDung;      // Công dụng
    private int hinhAnh;          // Ảnh minh hoạ

    public BaiThuoc(String tenBaiThuoc, String congDung, int hinhAnh) {
        this.tenBaiThuoc = tenBaiThuoc;
        this.congDung = congDung;
        this.hinhAnh = hinhAnh;
    }

    public String getTenBaiThuoc() { return tenBaiThuoc; }
    public String getCongDung() { return congDung; }
    public int getHinhAnh() { return hinhAnh; }
}
