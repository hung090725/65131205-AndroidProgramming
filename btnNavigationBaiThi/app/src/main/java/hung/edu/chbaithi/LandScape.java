package hung.edu.chbaithi;

/** Bước 1: Tạo file Model (LandScape.java)
 Mục tiêu: Đây là nơi bạn khai báo cho Android biết: "Một cảnh đẹp gồm những thông tin gì?".
 *
 * Model class để lưu trữ thông tin về một cảnh đẹp (Phiên bản chuyên nghiệp).
 */
public class LandScape {
    private String landscapeName;
    private String landscapeLocation; // Địa danh (ví dụ: Quảng Ninh)
    private float landscapeRating;     // Đánh giá (ví dụ: 4.8f)
    private int landscapeImage;        // ID hình ảnh

    public LandScape(String landscapeName, String landscapeLocation, float landscapeRating, int landscapeImage) {
        this.landscapeName = landscapeName;
        this.landscapeLocation = landscapeLocation;
        this.landscapeRating = landscapeRating;
        this.landscapeImage = landscapeImage;
    }

    public String getLandscapeName() {
        return landscapeName;
    }

    public void setLandscapeName(String landscapeName) {
        this.landscapeName = landscapeName;
    }

    public String getLandscapeLocation() {
        return landscapeLocation;
    }

    public void setLandscapeLocation(String landscapeLocation) {
        this.landscapeLocation = landscapeLocation;
    }

    public float getLandscapeRating() {
        return landscapeRating;
    }

    public void setLandscapeRating(float landscapeRating) {
        this.landscapeRating = landscapeRating;
    }

    public int getLandscapeImage() {
        return landscapeImage;
    }

    public void setLandscapeImage(int landscapeImage) {
        this.landscapeImage = landscapeImage;
    }
}
