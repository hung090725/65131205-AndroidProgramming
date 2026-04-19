package thigk2.nguyenvanhung;

/**
 * Model tin tuc tu RSS.
 */
public class RssItem {
    private String tieuDe;
    private String lienKet;
    private String moTa;

    public RssItem(String tieuDe, String lienKet, String moTa) {
        this.tieuDe = tieuDe;
        this.lienKet = lienKet;
        this.moTa = moTa;
    }

    public String getTieuDe() { return tieuDe; }
    public String getLienKet() { return lienKet; }
    public String getMoTa() { return moTa; }
}
