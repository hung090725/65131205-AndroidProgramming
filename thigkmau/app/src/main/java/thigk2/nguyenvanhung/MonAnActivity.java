package thigk2.nguyenvanhung;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Activity hien thi danh sach mon an tu JSON.
 * Code phong cach khong dau.
 */
public class MonAnActivity extends AppCompatActivity {

    private ListView lvMonAn;
    private ArrayList<MonAn> dsMonAn;
    private MonAnAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mon_an);

        anhXa();
        docDuLieuJson();
        thietLapDanhSach();
    }

    private void anhXa() {
        lvMonAn = findViewById(R.id.lvMonAn);
        dsMonAn = new ArrayList<>();
        findViewById(R.id.btnBackMonAn).setOnClickListener(v -> finish());
    }

    private void docDuLieuJson() {
        try {
            InputStream is = getAssets().open("mon_an.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, StandardCharsets.UTF_8);

            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                dsMonAn.add(new MonAn(
                        obj.getString("ten"),
                        obj.getString("moTa"),
                        obj.getString("hinhAnh")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void thietLapDanhSach() {
        adapter = new MonAnAdapter(this, R.layout.item_mon_an, dsMonAn);
        lvMonAn.setAdapter(adapter);

        lvMonAn.setOnItemClickListener((parent, view, position, id) -> {
            MonAn selected = dsMonAn.get(position);
            Intent intent = new Intent(this, ChiTietMonAnActivity.class);
            intent.putExtra("duLieuMonAn", selected);
            startActivity(intent);
        });
    }
}
