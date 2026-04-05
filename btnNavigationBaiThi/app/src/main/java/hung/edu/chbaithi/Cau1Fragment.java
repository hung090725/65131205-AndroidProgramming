package hung.edu.chbaithi;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cau1Fragment extends Fragment {

    // Khai báo các biến ở đầu lớp (Class level) để các hàm khác có thể nhìn thấy
    EditText editTextm;
    EditText editTextkm;
    Button btnChuyen;

    public Cau1Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout cho fragment
        View viewcau1 = inflater.inflate(R.layout.fragment_cau1, container, false);
        
        // b1: lấy id từ file xml
        editTextm = viewcau1.findViewById(R.id.editTextnhap_m);
        editTextkm = viewcau1.findViewById(R.id.editTextNhap_km);
        btnChuyen = viewcau1.findViewById(R.id.buttonChuyen);
        
        // b2: xử lý sự kiện lắng nghe khi nhấn nút
        btnChuyen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // lấy dữ liệu các ô đc nhập (dùng toString() để lấy chuỗi)
                String laydulieum = editTextm.getText().toString();
                String laydulieukm = editTextkm.getText().toString();

                // Logic: Ô nào có chữ thì ô đó sẽ là ô nguồn để tính ô kia
                if (!laydulieukm.isEmpty()) {
                    // TRƯỜNG HỢP 1: Có Km -> Tính Mét
                    double km = Double.parseDouble(laydulieukm);
                    double m = km * 1000.0; // Công thức chuyển km sang m
                    
                    // Gán kết quả vào ô m (dùng String.valueOf để chuyển số về lại chữ)
                    editTextm.setText(String.valueOf(m));
                    Toast.makeText(getActivity(), "Đã chuyển sang Mét", Toast.LENGTH_SHORT).show();
                    
                } else if (!laydulieum.isEmpty()) {
                    // TRƯỜNG HỢP 2: Có Mét -> Tính Km
                    double m = Double.parseDouble(laydulieum);
                    double km = m / 1000.0; // Công thức chuyển m sang km
                    
                    // Gán kết quả vào ô km
                    editTextkm.setText(String.valueOf(km));
                    Toast.makeText(getActivity(), "Đã chuyển sang Kilômét", Toast.LENGTH_SHORT).show();

                } else {
                    // TRƯỜNG HỢP 3: Cả hai đều rỗng
                    Toast.makeText(getActivity(), "Hãy nhập ít nhất một ô!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        return viewcau1;
    }
}