package hung.edu.buttonnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Cau2Fragment extends Fragment {

    public Cau2Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cau2, container, false);
        EditText edtHeightCm = view.findViewById(R.id.edtHeightCm);
        EditText edtWeightKg = view.findViewById(R.id.edtWeightKg);
        Button btnCalculateBmi = view.findViewById(R.id.btnCalculateBmi);
        TextView tvBmiResult = view.findViewById(R.id.tvBmiResult);

        btnCalculateBmi.setOnClickListener(v -> {
            String heightText = edtHeightCm.getText().toString().trim();
            String weightText = edtWeightKg.getText().toString().trim();
            if (heightText.isEmpty() || weightText.isEmpty()) {
                Toast.makeText(requireContext(), "Nhap du chieu cao va can nang", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double heightCm = Double.parseDouble(heightText);
                double weightKg = Double.parseDouble(weightText);
                double heightM = heightCm / 100.0;
                double bmi = weightKg / (heightM * heightM);

                String level;
                if (bmi < 18.5) {
                    level = "Gay";
                } else if (bmi < 25) {
                    level = "Binh thuong";
                } else if (bmi < 30) {
                    level = "Thua can";
                } else {
                    level = "Beo phi";
                }
                tvBmiResult.setText(String.format(java.util.Locale.US, "Ket qua: BMI = %.2f (%s)", bmi, level));
            } catch (NumberFormatException ex) {
                Toast.makeText(requireContext(), "So khong hop le", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}