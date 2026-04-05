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
 * Câu 3: Danh sách Bài Thuốc sử dụng RecyclerView
 * - Hiển thị danh sách các bài thuốc dân gian
 * - Khi nhấn vào một bài thuốc → Hiện Toast thông báo tên bài thuốc đó
 */
public class Cau3Fragment extends Fragment {

    private RecyclerView recyclerView;
    private BaiThuocAdapter adapter;
    private ArrayList<BaiThuoc> listData;

    public Cau3Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 1. Nạp giao diện
        View view = inflater.inflate(R.layout.fragment_cau3, container, false);

        // 2. Chuẩn bị dữ liệu danh sách bài thuốc
        khoiTaoDuLieu();

        // 3. Ánh xạ RecyclerView từ layout
        recyclerView = view.findViewById(R.id.recyclerViewBaiThuoc);

        // 4. Thiết lập kiểu danh sách đứng (từ trên xuống dưới)
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 5. Tạo Adapter và gán vào RecyclerView
        adapter = new BaiThuocAdapter(getContext(), listData);
        recyclerView.setAdapter(adapter);

        return view;
    }

    /**
     * Khởi tạo dữ liệu mẫu cho danh sách bài thuốc
     */
    private void khoiTaoDuLieu() {
        listData = new ArrayList<>();

        listData.add(new BaiThuoc(
                "Gừng trị cảm lạnh",
                "Uống trà gừng nóng khi bị cảm, giúp ra mồ hôi và giảm sốt",
                R.drawable.bai_thuoc_gung));

        listData.add(new BaiThuoc(
                "Nghệ chữa đau dạ dày",
                "Uống bột nghệ với mật ong mỗi sáng giúp làm lành vết loét dạ dày",
                R.drawable.bai_thuoc_nghe));

        listData.add(new BaiThuoc(
                "Tỏi kháng khuẩn",
                "Ăn tỏi tươi hoặc ngâm giấm giúp tăng đề kháng, kháng khuẩn tự nhiên",
                R.drawable.bai_thuoc_toi));

        listData.add(new BaiThuoc(
                "Gừng trị buồn nôn",
                "Nhai gừng tươi hoặc uống nước gừng giúp giảm buồn nôn hiệu quả",
                R.drawable.bai_thuoc_gung));

        listData.add(new BaiThuoc(
                "Nghệ trị viêm khớp",
                "Dùng nghệ cùng tiêu đen giúp chất curcumin hấp thu tốt hơn, giảm viêm",
                R.drawable.bai_thuoc_nghe));

        listData.add(new BaiThuoc(
                "Tỏi trị huyết áp cao",
                "Ăn 1-2 tép tỏi sống mỗi ngày giúp làm giãn mạch máu và hạ huyết áp",
                R.drawable.bai_thuoc_toi));
    }
}