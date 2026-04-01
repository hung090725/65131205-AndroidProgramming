package hung.edu.buttonnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Cau1Fragment extends Fragment {

    public Cau1Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cau1, container, false);
        EditText edtMeter = view.findViewById(R.id.edtMeter);
        EditText edtKm = view.findViewById(R.id.edtKm);
        Button btnConvert = view.findViewById(R.id.btnConvertLength);

        btnConvert.setOnClickListener(v -> {
            String meterText = edtMeter.getText().toString().trim();
            String kmText = edtKm.getText().toString().trim();

            if (meterText.isEmpty() && kmText.isEmpty()) {
                Toast.makeText(requireContext(), "Nhap m hoac km", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                if (!meterText.isEmpty()) {
                    double meter = Double.parseDouble(meterText);
                    double km = meter / 1000.0;
                    edtKm.setText(String.format(java.util.Locale.US, "%.3f", km));
                } else {
                    double km = Double.parseDouble(kmText);
                    double meter = km * 1000.0;
                    edtMeter.setText(String.format(java.util.Locale.US, "%.0f", meter));
                }
            } catch (NumberFormatException ex) {
                Toast.makeText(requireContext(), "Gia tri khong hop le", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}