package thigk2.nguyenvanhung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    // Khai bao cac CardView chuc nang
    CardView cardChucNang1, cardChucNang2, cardChucNang3, cardChucNang4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        
        // Setup padding cho system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Anh xa cac view tu layout
        timChieuCacView();

        // Thiet lap su kien click
        caiDatSuKien();
    }

    private void timChieuCacView() {
        cardChucNang1 = findViewById(R.id.card_chuc_nang_1);
        cardChucNang2 = findViewById(R.id.card_chuc_nang_2);
        cardChucNang3 = findViewById(R.id.card_chuc_nang_3);
        cardChucNang4 = findViewById(R.id.card_chuc_nang_4);
    }

    private void caiDatSuKien() {
        // Chuc nang 1: Tinh toan
        cardChucNang1.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChucNang1Activity.class);
            startActivity(intent);
        });

        // Chuc nang 2: Tinh thanh
        cardChucNang2.setOnClickListener(v -> {
            Toast.makeText(this, "Mo Chuc nang 2: Tinh thanh", Toast.LENGTH_SHORT).show();
        });

        // Chuc nang 3: Du lich
        cardChucNang3.setOnClickListener(v -> {
            Toast.makeText(this, "Mo Chuc nang 3: Du lich Nha Trang", Toast.LENGTH_SHORT).show();
        });

        // Chuc nang 4: Ca nhan
        cardChucNang4.setOnClickListener(v -> {
            Toast.makeText(this, "Mo Chuc nang 4: Thong tin ca nhan", Toast.LENGTH_SHORT).show();
        });
    }
}