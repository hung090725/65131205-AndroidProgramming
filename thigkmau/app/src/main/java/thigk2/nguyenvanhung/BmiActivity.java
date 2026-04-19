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

    private EditText ôNhậpChiềuCao, ôNhậpCânNặng;
    private Button nútTính;
    private TextView vănBảnChỉSố, vănBảnNhậnXét;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        ánhXạGiaoDiện();
        càiĐặtTínhToán();
    }

    private void ánhXạGiaoDiện() {
        ôNhậpChiềuCao = findViewById(R.id.editChieuCao);
        ôNhậpCânNặng = findViewById(R.id.editCanNang);
        nútTính = findViewById(R.id.nutTinhBmi);
        vănBảnChỉSố = findViewById(R.id.txtChiSoBmi);
        vănBảnNhậnXét = findViewById(R.id.txtNhanXet);
    }

    private void càiĐặtTínhToán() {
        nútTính.setOnClickListener(v -> {
            String vănBảnCao = ôNhậpChiềuCao.getText().toString();
            String vănBảnNặng = ôNhậpCânNặng.getText().toString();

            if (vănBảnCao.isEmpty() || vănBảnNặng.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            float chiềuCao = Float.parseFloat(vănBảnCao) / 100; // Đổi cm sang m
            float cânNặng = Float.parseFloat(vănBảnNặng);

            float bmi = cânNặng / (chiềuCao * chiềuCao);
            vănBảnChỉSố.setText(String.format("%.1f", bmi));

            phânLoạiBmi(bmi);
        });
    }

    private void phânLoạiBmi(float bmi) {
        String nhậnXét;
        if (bmi < 18.5) {
            nhậnXét = "Bạn hơi gầy, cần ăn uống thêm!";
        } else if (bmi < 24.9) {
            nhậnXét = "Tuyệt vời! Bạn có vóc dáng cân đối.";
        } else if (bmi < 29.9) {
            nhậnXét = "Bạn đang hơi thừa cân một chút.";
        } else {
            nhậnXét = "Cảnh báo! Bạn đang bị béo phì.";
        }
        vănBảnNhậnXét.setText(nhậnXét);
    }
}
