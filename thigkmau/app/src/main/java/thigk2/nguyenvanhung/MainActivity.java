package thigk2.nguyenvanhung;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Màn hình chính điều hướng ứng dụng.
 * Đã cập nhật theo mã nguồn và ID của người dùng cung cấp.
 */
public class MainActivity extends AppCompatActivity {

    private Button nutBmi, nutMonAn, nutBaiThuoc, nutGioiThieu, nutLamThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        khoiTaoDieuKhien();
        thietLapSuKien();
    }

    private void khoiTaoDieuKhien() {
        nutBmi = findViewById(R.id.button_BMI);
        nutMonAn = findViewById(R.id.button_monan);
        nutBaiThuoc = findViewById(R.id.button_thuoc);
        nutGioiThieu = findViewById(R.id.buttonGiơiThieu);
        nutLamThem = findViewById(R.id.buttonlamthem);
    }

    private void thietLapSuKien() {
        nutBmi.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BmiActivity.class);
            startActivity(intent);
        });

        nutMonAn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MonAnActivity.class);
            startActivity(intent);
        });

        nutBaiThuoc.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BaiThuocActivity.class);
            startActivity(intent);
        });

        nutGioiThieu.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GioiThieuActivity.class);
            startActivity(intent);
        });

        nutLamThem.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RssActivity.class);
            startActivity(intent);
        });
    }
}
