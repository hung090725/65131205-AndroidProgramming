package gk.onlai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BMI extends AppCompatActivity {
    EditText layidcannang, layidchieucao;
    Button layidbutton,laybuttonquaylai;
    TextView layidhienthi, layidphanloai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bmi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        layidcannang = findViewById(R.id.editCanNang);
        layidchieucao = findViewById(R.id.editChieuCao);
        layidbutton = findViewById(R.id.buttonBMI);
        layidhienthi = findViewById(R.id.textHienThikq);
        layidphanloai = findViewById(R.id.textViewPhanLoai);
        laybuttonquaylai = findViewById(R.id.buttonquaylai);
        laybuttonquaylai.setOnClickListener(v ->{
            Intent chuyenhuongquaylai = new Intent(BMI.this,MainActivity.class);
            startActivity(chuyenhuongquaylai);
        });
        layidbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lấy chuỗi từ edittext và kiẻm tra có rỗng ko
                String laychuoicannang = layidcannang.getText().toString();
                String laychuichieucao = layidchieucao.getText().toString();
                if (laychuoicannang.isEmpty() || laychuichieucao.isEmpty()) {
                    Toast.makeText(BMI.this, "vui lồng NHập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                // chuyển chuỗi sang số thực
                double sangsocannang = Double.parseDouble(laychuoicannang);
                double sangsochieucao = Double.parseDouble(laychuichieucao);
                // Xử lý nếu người dùng nhập chiều cao đơn vị cm thay vì mét
                // (Ví dụ: nhập 170 thay vì 1.7)

                double height = 0;
                if (sangsochieucao > 3) {
                    height = sangsochieucao / 100;
                }
                // coong thuc tinhs BMI
                Double BmiTinh = sangsocannang / (height * height);
                // hiển thị kết quả BMI
                layidhienthi.setText(String.format("Chỉ số BMI: %.2f",BmiTinh));

                // phan loại ket qua
                String phanLoai = "";
                if (BmiTinh < 18.5) {
                    phanLoai = "Phân loại: Gầy";
                } else if (BmiTinh >= 18.5 && BmiTinh < 25) {
                    phanLoai = "Phân loại: Bình thường";
                } else if (BmiTinh >= 25 && BmiTinh < 30) {
                    phanLoai = "Phân loại: Thừa cân";
                } else {
                    phanLoai = "Phân loại: Béo phì";
                }
                layidphanloai.setText(phanLoai);

            }
        });

    }
}