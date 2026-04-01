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

public class Cau3Fragment extends Fragment {

    public Cau3Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cau3, container, false);
        EditText edtA = view.findViewById(R.id.edtA);
        EditText edtB = view.findViewById(R.id.edtB);
        EditText edtC = view.findViewById(R.id.edtC);
        Button btnSolve = view.findViewById(R.id.btnSolve);
        TextView tvResult = view.findViewById(R.id.tvQuadraticResult);

        btnSolve.setOnClickListener(v -> {
            String aText = edtA.getText().toString().trim();
            String bText = edtB.getText().toString().trim();
            String cText = edtC.getText().toString().trim();
            if (aText.isEmpty() || bText.isEmpty() || cText.isEmpty()) {
                Toast.makeText(requireContext(), "Nhap day du a, b, c", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double a = Double.parseDouble(aText);
                double b = Double.parseDouble(bText);
                double c = Double.parseDouble(cText);

                if (a == 0) {
                    if (b == 0) {
                        tvResult.setText(c == 0 ? "Ket qua: Vo so nghiem" : "Ket qua: Vo nghiem");
                    } else {
                        double x = -c / b;
                        tvResult.setText(String.format(java.util.Locale.US, "Ket qua: x = %.3f", x));
                    }
                    return;
                }

                double delta = b * b - 4 * a * c;
                if (delta < 0) {
                    tvResult.setText("Ket qua: Vo nghiem");
                } else if (delta == 0) {
                    double x = -b / (2 * a);
                    tvResult.setText(String.format(java.util.Locale.US, "Ket qua: x1 = x2 = %.3f", x));
                } else {
                    double sqrtDelta = Math.sqrt(delta);
                    double x1 = (-b + sqrtDelta) / (2 * a);
                    double x2 = (-b - sqrtDelta) / (2 * a);
                    tvResult.setText(String.format(java.util.Locale.US, "Ket qua: x1 = %.3f, x2 = %.3f", x1, x2));
                }
            } catch (NumberFormatException ex) {
                Toast.makeText(requireContext(), "He so khong hop le", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}