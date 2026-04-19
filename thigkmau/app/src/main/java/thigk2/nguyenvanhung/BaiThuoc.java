package thigk2.nguyenvanhung;

import java.io.Serializable;

/**
 * Model BaiThuoc khong dau.
 */
public class BaiThuoc implements Serializable {
    private String ten;
    private String thoiGian;
    private String nguon;
    private String chiTiet;

    public BaiThuoc(String ten, String thoiGian, String nguon, String chiTiet) {
        this.ten = ten;
        this.thoiGian = thoiGian;
        this.nguon = nguon;
        this.chiTiet = chiTiet;
    }

    public String getTen() { return ten; }
    public String getThoiGian() { return thoiGian; }
    public String getNguon() { return nguon; }
    public String getChiTiet() { return chiTiet; }
}
