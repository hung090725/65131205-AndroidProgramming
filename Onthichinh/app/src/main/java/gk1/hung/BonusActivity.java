package gk1.hung;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class BonusActivity extends AppCompatActivity {

    private EditText edtGuess;
    private Button btnGuess;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bonusactivity);

        edtGuess = findViewById(R.id.edtGuess);
        btnGuess = findViewById(R.id.btnGuess);
        tvResult = findViewById(R.id.tvGuessResult);

        btnGuess.setOnClickListener(v -> {
            String input = edtGuess.getText().toString();
            if (!input.isEmpty()) {
                int userNum = Integer.parseInt(input);
                int magicNum = new Random().nextInt(10) + 1; // Số ngẫu nhiên 1-10

                if (userNum == magicNum) {
                    tvResult.setText('CHÚC MỪNG! Bạn đã đoán đúng số ' + magicNum);
                } else {
                    tvResult.setText('RẤT TIẾC! Số may mắn là ' + magicNum);
                }
            }
        });
    }
}
