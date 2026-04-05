package hung.edu.chbaithi;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Câu 4: Danh mục Món Ăn sử dụng RecyclerView.
 * Yêu cầu đề thi:
 * - Hiển thị danh sách và bắt sự kiện khi nhấn vào mục (1.5d)
 * - Chuyển nội dung sang ChiTietMonActivity bằng Intent thay cho Toast (0.5d)
 */
public class Cau4Fragment extends Fragment {

    private RecyclerView recyclerView;
    private MonAnAdapter adapter;
    private ArrayList<MonAn> listData;

    public Cau4Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 1. Nạp giao diện
        View view = inflater.inflate(R.layout.fragment_cau4, container, false);

        // 2. Chuẩn bị dữ liệu danh sách món ăn
        khoiTaoDuLieu();

        // 3. Ánh xạ RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewMonAn);

        // 4. Thiết lập kiểu danh sách đứng
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 5. Gán Adapter → Đây là nơi xử lý click và dùng Intent chuyển màn hình
        adapter = new MonAnAdapter(getContext(), listData);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void khoiTaoDuLieu() {
        listData = new ArrayList<>();

        listData.add(new MonAn(
                "Phở Bò",
                "Món phở truyền thống Hà Nội với nước dùng hầm xương, thịt bò tái và rau thơm. Hương vị đậm đà đặc trưng.",
                R.drawable.mon_an_pho));

        listData.add(new MonAn(
                "Bánh Mì",
                "Bánh mì Việt Nam với nhân thịt nguội, chả lụa, rau và tương ớt. Đặc sản nổi tiếng toàn thế giới.",
                R.drawable.mon_an_banhmi));

        listData.add(new MonAn(
                "Bánh Xèo",
                "Bánh xèo giòn rụm với nhân tôm, thịt, giá đỗ. Ăn kèm rau sống và nước mắm chua ngọt.",
                R.drawable.mon_an_banhxeo));

        listData.add(new MonAn(
                "Phở Gà",
                "Phở gà thanh đạm với nước dùng trong gà ngọt tự nhiên, thịt gà mềm và hành ngò thơm.",
                R.drawable.mon_an_pho));

        listData.add(new MonAn(
                "Bánh Mì Thịt Nướng",
                "Bánh mì với thịt nướng thơm lừng của miền Trung, kết hợp đồ chua và rau thơm.",
                R.drawable.mon_an_banhmi));

        listData.add(new MonAn(
                "Bánh Xèo Miền Tây",
                "Bánh xèo miền Tây kích thước lớn, nhân phong phú với tôm, thịt, nấm và rau đủ loại.",
                R.drawable.mon_an_banhxeo));
    }
}