package thigk2.nguyenvanhung;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChucNang1Fragment extends Fragment {

    EditText edtChieuDai, edtChieuRong;
    Button btnTinhToan;
    TextView tvChuVi, tvDienTich;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chuc_nang_1, container, false);

        // Anh xa view
        edtChieuDai = view.findViewById(R.id.edt_chieu_dai_frag);
        edtChieuRong = view.findViewById(R.id.edt_chieu_rong_frag);
        btnTinhToan = view.findViewById(R.id.btn_tinh_toan_frag);
        tvChuVi = view.findViewById(R.id.tv_chu_vi_frag);
        tvDienTich = view.findViewById(R.id.tv_dien_tich_frag);

        btnTinhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinhToan();
            }
        });

        return view;
    }

    private void tinhToan() {
        String sDai = edtChieuDai.getText().toString();
        String sRong = edtChieuRong.getText().toString();

        if (sDai.isEmpty() || sRong.isEmpty()) {
            Toast.makeText(getContext(), "Vui lòng nhập đầy đủ dài và rộng", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double dai = Double.parseDouble(sDai);
            double rong = Double.parseDouble(sRong);

            if (dai <= 0 || rong <= 0) {
                Toast.makeText(getContext(), "Dài và rộng phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                return;
            }

            double chuVi = (dai + rong) * 2;
            double dienTich = dai * rong;

            tvChuVi.setText("Chu vi: " + String.format("%.2f", chuVi) + " m");
            tvDienTich.setText("Diện tích: " + String.format("%.2f", dienTich) + " m²");

        } catch (NumberFormatException e) {
            Toast.makeText(getContext(), "Vui lòng nhập số hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }
}
