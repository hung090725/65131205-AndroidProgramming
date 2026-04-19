package thigk2.nguyenvanhung;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity xử lý tính toán chỉ số BMI.
 * Sử dụng phong cách Tiếng Việt trong đặt tên biến và hàm.
 */
public class BmiActivity extends AppCompatActivity {

    private EditText oNhapChieuCao, oNhapCanNang;
    private Button nutTinh;
    private TextView vanBanChiSo, vanBanNhanXet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        anhXaGiaoDien();
        caiDatTinhToan();
    }

    private void anhXaGiaoDien() {
        oNhapChieuCao = findViewById(R.id.editChieuCao);
        oNhapCanNang = findViewById(R.id.editCanNang);
        nutTinh = findViewById(R.id.nutTinhBmi);
        vanBanChiSo = findViewById(R.id.txtChiSoBmi);
        vanBanNhanXet = findViewById(R.id.txtNhanXet);
        findViewById(R.id.btnBackBmi).setOnClickListener(v -> finish());
    }

    private void caiDatTinhToan() {
        nutTinh.setOnClickListener(v -> {
            String vanBanCao = oNhapChieuCao.getText().toString();
            String vanBanNang = oNhapCanNang.getText().toString();

            if (vanBanCao.isEmpty() || vanBanNang.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            float chieuCao = Float.parseFloat(vanBanCao) / 100; // Đổi cm sang m
            float canNang = Float.parseFloat(vanBanNang);

            float bmi = canNang / (chieuCao * chieuCao);
            vanBanChiSo.setText(String.format("%.1f", bmi));

            phanLoaiBmi(bmi);
        });
    }

    private void phanLoaiBmi(float bmi) {
        String nhanXet;
        if (bmi < 18.5) {
            nhanXet = "Bạn hơi gầy, cần ăn uống thêm!";
        } else if (bmi < 24.9) {
            nhanXet = "Tuyệt vời! Bạn có vóc dáng cân đối.";
        } else if (bmi < 29.9) {
            nhanXet = "Bạn đang hơi thừa cân một chút.";
        } else {
            nhanXet = "Cảnh báo! Bạn đang bị béo phì.";
        }
        vanBanNhanXet.setText(nhanXet);
    }
}
