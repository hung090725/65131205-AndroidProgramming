package thigk2.nguyenvanhung;

import java.io.Serializable;

/**
 * Model MonAn khong dau.
 */
public class MonAn implements Serializable {
    private String ten;
    private String moTa;
    private String hinhAnh;

    public MonAn() {
    }

    public MonAn(String ten, String moTa, String hinhAnh) {
        this.ten = ten;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
