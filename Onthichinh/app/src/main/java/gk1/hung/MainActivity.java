package gk1.hung;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnBMI, btnFood, btnRemedy, btnInfo, btnBonus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo các view
        initViews();

        // Gán sự kiện click
        setClickListeners();
    }

    private void initViews() {
        btnBMI = findViewById(R.id.btnBMI);
        btnFood = findViewById(R.id.btnFood);
        btnRemedy = findViewById(R.id.btnRemedy);
        btnInfo = findViewById(R.id.btnInfo);
        btnBonus = findViewById(R.id.btnBonus);
    }

    private void setClickListeners() {
        btnBMI.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BMIActivity.class);
            startActivity(intent);
        });

        btnFood.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FoodActivity.class);
            startActivity(intent);
        });

        btnRemedy.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RemedyActivity.class);
            startActivity(intent);
        });

        btnInfo.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(intent);
        });

        btnBonus.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BonusActivity.class);
            startActivity(intent);
        });
    }
}