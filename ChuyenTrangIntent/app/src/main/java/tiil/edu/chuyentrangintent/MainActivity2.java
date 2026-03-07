package tiil.edu.chuyentrangintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // tìm đối tượng gắn bộ lắng nghe lưu trữ nó ở Button vì tìm ở button
        Button BtnMH2 = findViewById(R.id.btnmh2);
        // gắn bộ lắng nghe
        BtnMH2.setOnClickListener(BoLangNGheChuyenMH1);

    }

    View.OnClickListener BoLangNGheChuyenMH1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 1.tạo đối tượng Intent (Thu)
            // tham số 1 là hiện tại 2 là gửi tới(.class)
            Intent ThuKichHoatMH1 = new Intent(MainActivity2.this, MainActivity.class);
            // 2.Gửi Thư , mà không đợi phản hồi
            startActivity(ThuKichHoatMH1);
        }
    };
}