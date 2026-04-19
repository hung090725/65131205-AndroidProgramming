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

    private Button nútBmi, nútMónĂn, nútBàiThuốc, nútGiớiThiệu, nútLàmThêm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        khởiTạoĐiềuKhiển();
        thiếtLậpSựKiện();
    }

    private void khởiTạoĐiềuKhiển() {
        nútBmi = findViewById(R.id.button_BMI);
        nútMónĂn = findViewById(R.id.button_monan);
        nútBàiThuốc = findViewById(R.id.button_thuoc);
        nútGiớiThiệu = findViewById(R.id.buttonGiơiThieu);
        nútLàmThêm = findViewById(R.id.buttonlamthem);
    }

    private void thiếtLậpSựKiện() {
        nútBmi.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BmiActivity.class);
            startActivity(intent);
        });

        nútMónĂn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MonAnActivity.class);
            startActivity(intent);
        });

        nútBàiThuốc.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BaiThuocActivity.class);
            startActivity(intent);
        });

        nútGiớiThiệu.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, GioiThieuActivity.class);
            startActivity(intent);
        });

        nútLàmThêm.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RssActivity.class);
            startActivity(intent);
        });
    }
}
