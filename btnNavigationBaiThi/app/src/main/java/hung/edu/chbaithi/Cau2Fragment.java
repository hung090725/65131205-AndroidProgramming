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
 * Xử lý Câu 2: Hiển thị danh sách cảnh đẹp Việt Nam bằng RecyclerView chuyên nghiệp.
 */
public class Cau2Fragment extends Fragment {

    private RecyclerView recyclerView;
    private LandScapeAdapter adapter;
    private ArrayList<LandScape> listData;

    public Cau2Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 1. Nạp giao diện
        View view = inflater.inflate(R.layout.fragment_cau2, container, false);

        // 2. Chuẩn bị dữ liệu thật (Sử dụng hệ thống Rating và Location mới)
        initData();

        // 3. Ánh xạ và thiết lập RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewLandscape);
        
        // Thiết lập kiểu danh sách cuộn dọc
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 4. Gán Adapter
        adapter = new LandScapeAdapter(getContext(), listData);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void initData() {
        listData = new ArrayList<>();
        
        // Thêm các cảnh đẹp tiêu biểu với thông tin chi tiết
        listData.add(new LandScape("Vịnh Hạ Long", "Quảng Ninh", 4.9f, R.drawable.vinh_ha_long));
        listData.add(new LandScape("Phố Cổ Hội An", "Quảng Nam", 4.7f, R.drawable.hoi_an));
        listData.add(new LandScape("Đảo Phú Quốc", "Kiên Giang", 4.8f, R.drawable.phu_quoc));
        listData.add(new LandScape("Đà Lạt Ngàn Hoa", "Lâm Đồng", 4.6f, R.drawable.ic_launcher_background)); // Placeholder if needed
        listData.add(new LandScape("Sa Pa Sương Mù", "Lào Cai", 4.5f, R.drawable.ic_launcher_background));
    }
}