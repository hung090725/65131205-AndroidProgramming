package thigk2.nguyenvanhung;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Activity hien thi danh sach bai thuoc dung RecyclerView.
 * Code phong cach khong dau.
 */
public class BaiThuocActivity extends AppCompatActivity {

    private RecyclerView rvBaiThuoc;
    private BaiThuocAdapter adapter;
    private List<BaiThuoc> dsBaiThuoc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_thuoc);

        anhXa();
        taoDuLieuMau();
        thietLapRecyclerView();
    }

    private void anhXa() {
        rvBaiThuoc = findViewById(R.id.rvBaiThuoc);
        findViewById(R.id.btnBackBaiThuoc).setOnClickListener(v -> finish());
    }

    private void taoDuLieuMau() {
        dsBaiThuoc = new ArrayList<>();
        dsBaiThuoc.add(new BaiThuoc("Bai thuoc tri cam lanh", "15 phut", "Dan gian", "Su dung gung tuoi Thai lat, dun soi voi nuoc..."));
        dsBaiThuoc.add(new BaiThuoc("Bai thuoc tri dau bung", "10 phut", "Y hoc co truyen", "Dung la mo rau, thai nho tron trung ga..."));
        dsBaiThuoc.add(new BaiThuoc("Bai thuoc giai ruou", "5 phut", "Dan gian", "Nuoc chanh muoi hoac nuoc dau xanh..."));
        dsBaiThuoc.add(new BaiThuoc("Bai thuoc tri ho", "20 phut", "Y hoc co truyen", "Dung quat chung duong phen hoac mat ong..."));
    }

    private void thietLapRecyclerView() {
        adapter = new BaiThuocAdapter(this, dsBaiThuoc, baiThuoc -> {
            Intent intent = new Intent(this, ChiTietBaiThuocActivity.class);
            intent.putExtra("duLieuBaiThuoc", baiThuoc);
            startActivity(intent);
        });
        rvBaiThuoc.setLayoutManager(new LinearLayoutManager(this));
        rvBaiThuoc.setAdapter(adapter);
    }
}
