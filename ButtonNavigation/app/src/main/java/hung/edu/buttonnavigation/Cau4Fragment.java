package hung.edu.buttonnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Random;

public class Cau4Fragment extends Fragment {
    private static final String[] GREETINGS = {
            "Chuc ban hoc tot hom nay!",
            "Ban dang lam rat tot!",
            "Co len, sap xong bai roi!",
            "Hom nay la mot ngay dep troi!"
    };

    public Cau4Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cau4, container, false);
        Button btnRandom = view.findViewById(R.id.btnRandomGreeting);
        TextView tvGreeting = view.findViewById(R.id.tvGreetingResult);
        Random random = new Random();

        btnRandom.setOnClickListener(v -> {
            String greeting = GREETINGS[random.nextInt(GREETINGS.length)];
            tvGreeting.setText(greeting);
        });

        return view;
    }
}