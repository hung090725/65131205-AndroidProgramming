package thigk2.nguyenvanhung;

import java.io.Serializable;

/**
 * Lớp Model đại diện cho Sinh viên.
 * Sử dụng phong cách Tiếng Việt như yêu cầu.
 */
public class SinhVien implements Serializable {
    private String maSV;
    private String hoTen;
    private String lop;

    public SinhVien() {
    }

    public SinhVien(String maSV, String hoTen, String lop) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.lop = lop;
    }

    public String layMaSV() {
        return maSV;
    }

    public void ganMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String layHoTen() {
        return hoTen;
    }

    public void ganHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String layLop() {
        return lop;
    }

    public void ganLop(String lop) {
        this.lop = lop;
    }

    @Override
    public String toString() {
        return hoTen + " - " + maSV;
    }
}
