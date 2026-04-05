package hung.edu.chbaithi;

/**
 * Model class: Lưu thông tin một món ăn
 */
public class MonAn {
    private String tenMonAn;
    private String moTa;
    private int hinhAnh;

    public MonAn(String tenMonAn, String moTa, int hinhAnh) {
        this.tenMonAn = tenMonAn;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
    }

    public String getTenMonAn() { return tenMonAn; }
    public String getMoTa() { return moTa; }
    public int getHinhAnh() { return hinhAnh; }
}
