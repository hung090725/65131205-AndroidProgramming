package thigk2.nguyenvanhung;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        CardView cardHinhHoc   = view.findViewById(R.id.card_hinh_hoc);
        CardView cardTinhThanh = view.findViewById(R.id.card_tinh_thanh);
        CardView cardDuLich    = view.findViewById(R.id.card_du_lich);
        CardView cardCaNhan    = view.findViewById(R.id.card_ca_nhan);

        cardHinhHoc.setOnClickListener(v -> navigate(R.id.nav_hinh_hoc));
        cardTinhThanh.setOnClickListener(v -> navigate(R.id.nav_tinh_thanh));
        cardDuLich.setOnClickListener(v -> navigate(R.id.nav_du_lich));
        cardCaNhan.setOnClickListener(v -> navigate(R.id.nav_ca_nhan));

        return view;
    }

    private void navigate(int navId) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).navigateTo(navId);
        }
    }
}
