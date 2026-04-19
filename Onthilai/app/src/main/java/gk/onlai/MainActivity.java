package gk.onlai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button bmi;
    Button monan;
    Button gioithieu;
    Button thuoc;
    Button lamthem;

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
        bmi = findViewById(R.id.button_BMI);
        monan = findViewById(R.id.button_monan);
        gioithieu = findViewById(R.id.buttonGiơiThieu);
        thuoc = findViewById(R.id.button_thuoc);
        lamthem = findViewById(R.id.buttonlamthem);
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"sự kiện BMI được click",Toast.LENGTH_SHORT).show();
                Intent chuyensangBMI = new Intent(MainActivity.this,BMI.class);
                startActivity(chuyensangBMI);
            }
        });
        monan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"sự kiện món ăn được click",Toast.LENGTH_SHORT).show();
                Intent chuenmonan = new Intent(MainActivity.this, FoodActivity.class);
                startActivity(chuenmonan);
            }
        });
        gioithieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"sự kiện giới thiệu được click",Toast.LENGTH_SHORT).show();
                Intent chuyensanggioithieu = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(chuyensanggioithieu);
            }
        });
        thuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"sự kiện thuốc được click",Toast.LENGTH_SHORT).show();
                Intent chuyensangthuoc = new Intent(MainActivity.this, RemedyActivity.class);
                startActivity(chuyensangthuoc);
            }
        });
        lamthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"sự kiện làm thêm được click",Toast.LENGTH_SHORT).show();
                Intent lamthem = new Intent(MainActivity.this,BonusActivity.class);
                startActivity(lamthem);
            }

        });

    }
}