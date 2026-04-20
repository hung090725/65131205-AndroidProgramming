package thigk2.nguyenvanhung;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class ChucNang3Fragment extends Fragment {

    ListView lvDuLich;
    ArrayList<DiaDiem> dsDiaDiem;
    DuLichAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chuc_nang_3, container, false);

        lvDuLich = view.findViewById(R.id.lv_du_lich_frag);

        dsDiaDiem = new ArrayList<>();
        dsDiaDiem.add(new DiaDiem(R.drawable.dl_thap_tram_huong, "Tháp Trầm Hương", "Đường Trần Phú, Lộc Thọ, Nha Trang"));
        dsDiaDiem.add(new DiaDiem(R.drawable.dl_vinpearl, "Vinpearl Nha Trang", "Đảo Hòn Tre, Vĩnh Nguyên, Nha Trang"));
        dsDiaDiem.add(new DiaDiem(R.drawable.dl_vien_hai_duong, "Viện Hải Dương Học", "01 Cầu Đá, Vĩnh Nguyên, Nha Trang"));
        dsDiaDiem.add(new DiaDiem(R.drawable.dl_nha_trang, "Tháp Bà Ponagar", "2 Thắng 4, Vĩnh Phước, Nha Trang"));
        dsDiaDiem.add(new DiaDiem(R.drawable.dl_vinh_nha_trang, "Vịnh Nha Trang", "Thành phố Nha Trang, Khánh Hòa"));

        adapter = new DuLichAdapter(getContext(), R.layout.item_du_lich, dsDiaDiem);
        lvDuLich.setAdapter(adapter);

        lvDuLich.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), "Bạn chọn: " + dsDiaDiem.get(position).getTenDiaDiem(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
