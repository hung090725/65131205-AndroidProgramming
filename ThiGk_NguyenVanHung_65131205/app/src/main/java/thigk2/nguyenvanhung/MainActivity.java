package thigk2.nguyenvanhung;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottom_navigation);

        // Nap HomeFragment mac dinh khi lan dau mo app
        if (savedInstanceState == null) {
            replaceFragment(new HomeFragment());
        }

        bottomNav.setOnItemSelectedListener(this::handleNavigation);
    }

    private boolean handleNavigation(@NonNull MenuItem item) {
        Fragment fragment;
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
        } else if (id == R.id.nav_hinh_hoc) {
            fragment = new ChucNang1Fragment();
        } else if (id == R.id.nav_tinh_thanh) {
            fragment = new ChucNang2Fragment();
        } else if (id == R.id.nav_du_lich) {
            fragment = new ChucNang3Fragment();
        } else if (id == R.id.nav_ca_nhan) {
            fragment = new ChucNang4Fragment();
        } else {
            return false;
        }

        replaceFragment(fragment);
        return true;
    }

    // Goi tu HomeFragment khi nhan CardView
    public void navigateTo(int navItemId) {
        bottomNav.setSelectedItemId(navItemId);
    }

    @Override
    public void onBackPressed() {
        if (bottomNav.getSelectedItemId() != R.id.nav_home) {
            bottomNav.setSelectedItemId(R.id.nav_home);
        } else {
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
            .beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(R.id.fragment_container, fragment)
            .commitAllowingStateLoss();
    }
}