package thigk2.nguyenvanhung;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity hien thi thong tin ca nhan sinh vien.
 */
public class GioiThieuActivity extends AppCompatActivity {

    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioi_thieu);

        btnBack = findViewById(R.id.btnBackMain);
        btnBack.setOnClickListener(v -> finish());
    }
}
