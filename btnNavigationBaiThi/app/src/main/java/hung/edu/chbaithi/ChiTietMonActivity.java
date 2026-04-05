package hung.edu.chbaithi;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Màn hình Chi Tiết Món Ăn.
 * Được mở khi người dùng nhấn vào một món ăn trong RecyclerView.
 * Nhận dữ liệu từ Intent được gửi qua từ MonAnAdapter.
 */
public class ChiTietMonActivity extends AppCompatActivity {

    // Tên "chìa khoá" để truyền và nhận dữ liệu qua Intent
    // (Hai bên phải dùng CÙNG TÊN KEY này)
    public static final String KEY_TEN = "ten_mon_an";
    public static final String KEY_MO_TA = "mo_ta";
    public static final String KEY_HINH_ANH = "hinh_anh";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon);

        // Ánh xạ các View
        TextView tvTen = findViewById(R.id.tvChiTietTen);
        TextView tvMoTa = findViewById(R.id.tvChiTietMoTa);
        ImageView ivAnh = findViewById(R.id.ivChiTietAnh);
        TextView btnBack = findViewById(R.id.btnBack);

        // Nhận dữ liệu từ Intent được gửi qua
        // getIntent(): Lấy cái "phong bì" chứa dữ liệu
        // getStringExtra(KEY): Mở phong bì, lấy dữ liệu theo đúng tên KEY
        String ten = getIntent().getStringExtra(KEY_TEN);
        String moTa = getIntent().getStringExtra(KEY_MO_TA);
        int hinhAnh = getIntent().getIntExtra(KEY_HINH_ANH, R.drawable.ic_launcher_background);

        // Hiện dữ liệu lên màn hình
        tvTen.setText(ten);
        tvMoTa.setText(moTa);
        ivAnh.setImageResource(hinhAnh);

        // Xử lý nút Quay lại → Đóng màn hình này, quay về danh sách
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // finish() = Đóng Activity hiện tại, quay về Activity trước
            }
        });
    }
}
