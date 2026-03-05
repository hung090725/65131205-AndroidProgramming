package tiil.edu.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> dsTenTinhThanhVN;

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
        //Hiển thị dữ liệu lên listView
        // BƯỚC 1 (Nguồn dữ liệu) cần có dữ liệu 1 là dùng csdl 2 là viết sẵn(Hard-code)
        // 1 dùng ArrayList<kiểu trả về của arraylist> ten bien
        // Dữ liệu (ArrayList): Là những món ăn bạn có trong bếp.
        //•
        //ListView: Là cái bàn ăn (nơi trưng bày).
        //•
        //ArrayAdapter: Chính là người phục vụ. Người này sẽ lấy từng món ăn,
        // đặt vào một chiếc đĩa (layout của một dòng), rồi bưng ra đặt lên bàn (ListView).

        //khai báo phải thêm mới
        dsTenTinhThanhVN = new ArrayList<String>();
        // Đans lẻ phải đọc từ nguồn đây làm đơn giản
        dsTenTinhThanhVN.add("Hà Nội");
        dsTenTinhThanhVN.add("Hồ Chí Minh");
        dsTenTinhThanhVN.add("Đà Nẵng");
        dsTenTinhThanhVN.add("Cần Thơ");
        dsTenTinhThanhVN.add("Bắc Giang");
        dsTenTinhThanhVN.add("Bắc Ninh");
        //
        //BƯỚC 2 TẠO Adapter dùng ArrayAdapter
        //ArrayAdapter thực hiện 2 việc chính:
        //1.Đọc dữ liệu: Nó duyệt qua từng phần tử trong danh sách (ArrayList).
        //2.Tạo View: Với mỗi phần tử, nó sẽ tạo ra một giao diện (thường là một TextView) và gán giá trị của phần tử đó vào giao diện này.
        /*Tham số 1 (Context): Để Adapter biết nó đang chạy ở màn hình nào (thường dùng this).
Tham số 2 (layout): Đây là tệp XML định nghĩa giao diện cho một dòng trong danh sách.
 android.R.layout.simple_list_item_1 là một mẫu có sẵn của hệ thống Android (chỉ chứa 1 cái TextView duy nhất).
Tham số 3 (List): Danh sách dữ liệu thực tế mà bạn muốn hiển thị.*/
        /* tham số 2 Nó thường dùng khi bạn chỉ cần hiển thị một chuỗi văn bản (String) đơn giản trên mỗi dòng.*/
        ArrayAdapter<String> adapterTT = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                dsTenTinhThanhVN);
        //  bước 3 gắn vào điều khiển hiển thị ở đây hiển thị lên listView nên dùng nó trả vèe luôn
        // thamn chiếu trước dùng findViewBYid(R.id.tenid)
        ListView lvTT = findViewById(R.id.lvDanhSachTT);
        // do adaoterTT đang chứa toàn bộ nên dùng nó luôn từ nguồn qua adapter rồi qua listView
        lvTT.setAdapter(adapterTT);

        // gắn bộ lắng nghe vào listView
        lvTT.setOnItemClickListener(BoLangNghe);

    }

    // tạo bộ lắng nghe và xử lý sự kiện onItemClick , đặt vào 1 biến
    AdapterView.OnItemClickListener BoLangNghe = new AdapterView.OnItemClickListener() {

        @Override
        // chỗ vt 3 position chỉ vị trí item dc chọn khi chạm vào cất vào biến position tên khác cũng dck
        // là phần từ vừa click á
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // ví dụ ở đây, là hiện lên màng hình  một thông báo nhanh  về vị trí của phần tử vừa chọn  . và giá trị của phần tử
            // hiện nhanh một thông báo và tự tắt đi dùng Toast.MakeText chức năng tạo thông báo
            // this là của cái nào file á ở đay của mainActivitỵ tham số 1 chỉ file 2 chỉ chuỗi hiển thị 3 là thời gian
            // đoạn dưới chỉ làm vb thôi
            //
            //nhớ để biến arraylist ở toàn cuc khi làm giá trị
            // cách 2: String tentinh = dsTenTinhThanhVN.get(position);

            Toast.makeText(MainActivity.this, "Vị trí Hưng Chọn: " + position + " Giá trị Hưng Chọn là : " + parent.getItemAtPosition(position), Toast.LENGTH_LONG).show();

        }
    };
}