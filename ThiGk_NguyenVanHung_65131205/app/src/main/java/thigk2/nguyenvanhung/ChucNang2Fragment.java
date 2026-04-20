package thigk2.nguyenvanhung;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ChucNang2Fragment extends Fragment {

    ListView lvTinhThanh;
    ArrayList<String> dsTinhThanh;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chuc_nang_2, container, false);

        lvTinhThanh = view.findViewById(R.id.lv_tinh_thanh_frag);

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
        dsTinhThanh.add("Nguyễn Văn Hưng (65131205)");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dsTinhThanh);
        lvTinhThanh.setAdapter(adapter);

        lvTinhThanh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Bạn chọn: " + dsTinhThanh.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
