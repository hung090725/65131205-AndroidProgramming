package thigk2.nguyenvanhung;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity hien thi chi tiet mon an.
 */
public class ChiTietMonAnActivity extends AppCompatActivity {

    private ImageView imgHinhAnh;
    private TextView txtTen, txtMoTa;
    private Button btnQuayLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon_an);

        anhXa();
        hienThiDuLieu();
        suKien();
    }

    private void anhXa() {
        imgHinhAnh = findViewById(R.id.imgChiTiet);
        txtTen = findViewById(R.id.txtTenChiTiet);
        txtMoTa = findViewById(R.id.txtMoTaChiTiet);
        btnQuayLai = findViewById(R.id.btnQuayLai);
    }

    private void hienThiDuLieu() {
        MonAn monAn = (MonAn) getIntent().getSerializableExtra("duLieuMonAn");
        if (monAn != null) {
            txtTen.setText(monAn.getTen());
            txtMoTa.setText(monAn.getMoTa());
            // Tam thoi dung logo_app
            imgHinhAnh.setImageResource(R.drawable.logo_app);
        }
    }

    private void suKien() {
        btnQuayLai.setOnClickListener(v -> finish());
    }
}
