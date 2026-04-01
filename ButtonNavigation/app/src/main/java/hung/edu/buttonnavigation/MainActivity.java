 package hung.edu.buttonnavigation;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

 public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment;
                int itemId = menuItem.getItemId();

                if (itemId == R.id.navigation_cau1) {
                    selectedFragment = new Cau1Fragment();
                } else if (itemId == R.id.navigation_cau2) {
                    selectedFragment = new Cau2Fragment();
                } else if (itemId == R.id.navigation_cau3) {
                    selectedFragment = new Cau3Fragment();
                } else if (itemId == R.id.navigation_cau4) {
                    selectedFragment = new Cau4Fragment();
                } else {
                    selectedFragment = new QuestionsFragment();
                }

                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
                return true;
            }
        });

        if (savedInstanceState == null) {
            bottomNav.setSelectedItemId(R.id.navigation_welcome);
        }
    }
}