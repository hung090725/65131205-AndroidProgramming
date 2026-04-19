package gk1.hung;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BMIActivity extends AppCompatActivity {

    // 1. KHAI BÁO
    private EditText edtHeight, edtWeight;
    private Button btnCalculate;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        // 2. ÁNH XẠ
        edtHeight = findViewById(R.id.edtHeight);
        edtWeight = findViewById(R.id.edtWeight);
        btnCalculate = findViewById(R.id.btnCalculate);
        tvResult = findViewById(R.id.tvResult);

        // 3. CÀI ĐẶT SỰ KIỆN
        btnCalculate.setOnClickListener(v -> {
            tinhToanBMI();
        });
    }

    private void tinhToanBMI() {
        String sHeight = edtHeight.getText().toString();
        String sWeight = edtWeight.getText().toString();

        if (sHeight.isEmpty() || sWeight.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double h = Double.parseDouble(sHeight);
            double w = Double.parseDouble(sWeight);

            if (h <= 0) {
                Toast.makeText(this, "Chiều cao phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                return;
            }

            double bmi = w / (h * h);

            String status = "";
            if (bmi < 18.5) {
                status = "GẦY";
            } else if (bmi < 24.9) {
                status = "BÌNH THƯỜNG";
            } else if (bmi < 29.9) {
                status = "THỪA CÂN";
            } else {
                status = "BÉO PHÌ";
            }

            tvResult.setText(String.format("Chỉ số BMI: %.2f\nTrạng thái: %s", bmi, status));

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Dữ liệu nhập vào không hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }
}
