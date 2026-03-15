package tiil.edu.viewpager2tablayoutfragment;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<QuocGia> dsQuocGia;
    ViewPager2 viewPager2;
    TabLayout tabLayout; // Đã sửa từ TableLayout sang TabLayout
    QuocGiaPagerAdapter quocGiaPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        
        dsQuocGia = new ArrayList<QuocGia>();
        // Lưu ý: Tên drawable (Vn -> vn) nên viết thường để tránh lỗi Resource Not Found
        QuocGia qg1 = new QuocGia("Vietnam","vn","80");
        QuocGia qg2 = new QuocGia("Thailand","tl","68");
        QuocGia qg3 = new QuocGia("Malaysia","malaysia","30");
        dsQuocGia.add(qg1);
        dsQuocGia.add(qg2);
        dsQuocGia.add(qg3);

        viewPager2 = findViewById(R.id.viewPagerQG);
        quocGiaPagerAdapter = new QuocGiaPagerAdapter(this, dsQuocGia);
        viewPager2.setAdapter(quocGiaPagerAdapter);

        tabLayout = findViewById(R.id.tabQuocGia);
        
        // Sửa cú pháp TabLayoutMediator và đổi sang tab.setText
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            tab.setText("QG " + (position + 1));
        }).attach();
    }
}
