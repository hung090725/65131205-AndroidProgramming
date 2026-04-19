package gk1.hung;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import gk1.hung.adapters.RemedyAdapter;
import gk1.hung.models.Remedy;

public class RemedyActivity extends AppCompatActivity {

    private RecyclerView rvRemedy;
    private RemedyAdapter adapter;
    private List<Remedy> remedyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remedyactivity);

        rvRemedy = findViewById(R.id.rvRemedy);

        remedyList = new ArrayList<>();
        remedyList.add(new Remedy('Bài thuốc 1: Trà thảo mộc', '10 phút', 'ic_launcher_background'));
        remedyList.add(new Remedy('Bài thuốc 2: Massage cổ', '20 phút', 'ic_launcher_background'));
        remedyList.add(new Remedy('Bài thuốc 3: Ngâm chân', '15 phút', 'ic_launcher_background'));

        rvRemedy.setLayoutManager(new LinearLayoutManager(this));

        adapter = new RemedyAdapter(this, remedyList);
        rvRemedy.setAdapter(adapter);
    }
}
