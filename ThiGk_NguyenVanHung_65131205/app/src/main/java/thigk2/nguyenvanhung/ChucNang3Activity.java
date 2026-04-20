package thigk2.nguyenvanhung;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ChucNang3Activity extends AppCompatActivity {

    ListView lvDuLich;
    Button btnBack;
    ArrayList<DiaDiem> dsDiaDiem;
    DuLichAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuc_nang_3);

        // Anh xa view
        lvDuLich = findViewById(R.id.lv_du_lich);
        btnBack = findViewById(R.id.btn_back_3);

        // Khoi tao du lieu hard-code 5 dia diem
        dsDiaDiem = new ArrayList<>();
        dsDiaDiem.add(new DiaDiem(R.drawable.dl_thap_tram_huong, "Tháp Trầm Hương", "Đường Trần Phú, Lộc Thọ, Nha Trang"));
        dsDiaDiem.add(new DiaDiem(R.drawable.dl_vinpearl, "Vinpearl Nha Trang", "Đảo Hòn Tre, Vĩnh Nguyên, Nha Trang"));
        dsDiaDiem.add(new DiaDiem(R.drawable.dl_vien_hai_duong, "Viện Hải Dương Học", "01 Cầu Đá, Vĩnh Nguyên, Nha Trang"));
        dsDiaDiem.add(new DiaDiem(R.drawable.dl_nha_trang, "Tháp Bà Ponagar", "2 Thắng 4, Vĩnh Phước, Nha Trang"));
        dsDiaDiem.add(new DiaDiem(R.drawable.dl_vinh_nha_trang, "Vịnh Nha Trang", "Thành phố Nha Trang, Khánh Hòa"));

        // Thiet lap Adapter
        adapter = new DuLichAdapter(this, R.layout.item_du_lich, dsDiaDiem);
        lvDuLich.setAdapter(adapter);

        // Su kien click
        lvDuLich.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ChucNang3Activity.this, "Bạn chọn: " + dsDiaDiem.get(position).getTenDiaDiem(), Toast.LENGTH_SHORT).show();
            }
        });

        // Su kien quay lai
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
