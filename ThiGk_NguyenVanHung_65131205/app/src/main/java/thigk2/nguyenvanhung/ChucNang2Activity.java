package thigk2.nguyenvanhung;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ChucNang2Activity extends AppCompatActivity {

    ListView lvTinhThanh;
    Button btnQuayLai;
    ArrayList<String> dsTinhThanh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuc_nang_2);

        // Anh xa view
        lvTinhThanh = findViewById(R.id.lv_tinh_thanh);
        btnQuayLai = findViewById(R.id.btn_quay_lai_2);

        // Khoi tao du lieu (Hard-code 10 tinh thanh)
        dsTinhThanh = new ArrayList<>();
        dsTinhThanh.add("Hà Nội");
        dsTinhThanh.add("TP. Hồ Chí Minh");
        dsTinhThanh.add("Đà Nẵng");
        dsTinhThanh.add("Nha Trang");
        dsTinhThanh.add("Hải Phòng");
        dsTinhThanh.add("Cần Thơ");
        dsTinhThanh.add("Huế");
        dsTinhThanh.add("Đà Lạt");
        dsTinhThanh.add("Vũng Tàu");
        dsTinhThanh.add("Nguyễn Văn Hưng"); // Ten sinh vien dac biet

        // Thiet lap Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dsTinhThanh
        );

        lvTinhThanh.setAdapter(adapter);

        // Su kien click vao item
        lvTinhThanh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tenChon = dsTinhThanh.get(position);
                Toast.makeText(ChucNang2Activity.this, "Bạn đã chọn: " + tenChon, Toast.LENGTH_SHORT).show();
            }
        });

        // Su kien quay lai
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
