package thigk2.nguyenvanhung;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity hien thi chi tiet bai thuoc.
 */
public class ChiTietBaiThuocActivity extends AppCompatActivity {

    private TextView txtTen, txtNguon, txtNoiDung;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_bai_thuoc);

        anhXa();
        hienThiDuLieu();
        suKien();
    }

    private void anhXa() {
        txtTen = findViewById(R.id.txtTenChiTiet);
        txtNguon = findViewById(R.id.txtNguonChiTiet);
        txtNoiDung = findViewById(R.id.txtNoiDungChiTiet);
        btnBack = findViewById(R.id.btnBack);
    }

    private void hienThiDuLieu() {
        BaiThuoc baiThuoc = (BaiThuoc) getIntent().getSerializableExtra("duLieuBaiThuoc");
        if (baiThuoc != null) {
            txtTen.setText(baiThuoc.getTen());
            txtNguon.setText("Nguon: " + baiThuoc.getNguon());
            txtNoiDung.setText(baiThuoc.getChiTiet());
        }
    }

    private void suKien() {
        btnBack.setOnClickListener(v -> finish());
    }
}
