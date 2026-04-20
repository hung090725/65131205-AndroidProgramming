package thigk2.nguyenvanhung;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ChucNang1Activity extends AppCompatActivity {

    EditText edtChieuDai, edtChieuRong;
    Button btnTinhToan, btnQuayLai;
    TextView tvChuVi, tvDienTich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuc_nang_1);

        // Anh xa view
        edtChieuDai = findViewById(R.id.edt_chieu_dai);
        edtChieuRong = findViewById(R.id.edt_chieu_rong);
        btnTinhToan = findViewById(R.id.btn_tinh_toan);
        btnQuayLai = findViewById(R.id.btn_quay_lai_1);
        tvChuVi = findViewById(R.id.tv_chu_vi);
        tvDienTich = findViewById(R.id.tv_dien_tich);

        // Su kien tinh toan
        btnTinhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinhToan();
            }
        });

        // Su kien quay lai
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Quay lai man hinh truoc
            }
        });
    }

    private void tinhToan() {
        String sDai = edtChieuDai.getText().toString();
        String sRong = edtChieuRong.getText().toString();

        if (sDai.isEmpty() || sRong.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ dài và rộng", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double dai = Double.parseDouble(sDai);
            double rong = Double.parseDouble(sRong);

            if (dai <= 0 || rong <= 0) {
                Toast.makeText(this, "Dài và rộng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                return;
            }

            double chuVi = (dai + rong) * 2;
            double dienTich = dai * rong;

            // Hien thi ket qua
            tvChuVi.setText("Chu vi: " + String.format("%.2f", chuVi) + " m");
            tvDienTich.setText("Diện tích: " + String.format("%.2f", dienTich) + " m²");

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Vui lòng nhập số hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }
}
