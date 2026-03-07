package tiil.edu.chuyentrangintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }
    public void ChuyenSangMH2(View v){
        // 1.tạo đối tượng Intent (Thu)
        // tham số 1 là hiện tại 2 là gửi tới(.class)
        Intent ThuKichHoatMH2 = new Intent(MainActivity.this, // MH hiện  tại
                                                    MainActivity2.class); // màng hình chuyển tới
        // 2.Gửi Thư , mà không đợi phản hồi
        startActivity(ThuKichHoatMH2);
    }
}