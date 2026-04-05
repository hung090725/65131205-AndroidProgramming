package hung.edu.chbaithi;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this); // Tạm thời tắt dòng này để tránh lỗi màn hình đen
        setContentView(R.layout.activity_main);
//        Nếu bạn chỉ vẽ thanh menu trong XML, nó chỉ là một "tấm ảnh" tĩnh hiện lên màn hình.
//                Để thanh menu đó "sống" và hoạt động được (bấm vào là chuyển trang), bạn phải mang nó vào code Java để điều khiển.
//
//        Phải lấy nó ra thì mới cài đặt được sự kiện: bottomNav.setOnItemSelectedListener(...). Nếu không có dòng này, code Java
//        sẽ không biết thanh menu nằm ở đâu để mà lắng nghe người dùng bấm vào.
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);

        // Tự động nạp HomeFragment khi vừa mở ứng dụng
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.noihienthifragment, new HomeFragment())
                    .commit();
        }

        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
//            Giải thích: Bạn đang gắn một cái "Tai" vào thanh menu. Cái Tai này luôn lắng nghe:
//                "Khi nào người dùng bấm vào một icon trên menu thì hãy báo cho tôi biết!".
//                    4. Xử lý khi có người bấm (Hàm onNavigationItemSelected)
            //menuItem Một cái Icon cụ thể (Ví dụ:Icon hình Ngôi nhà).
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                Fragment selectedFragment = null; Giải thích: Bạn chuẩn bị một cái khay trống để đựng trang (Fragment) sắp được chọn.
                Fragment selectedFragment = null;

//                Giải thích: Mỗi icon trên thanh menu có một cái ID riêng (như số báo danh).
//                Dòng này giúp bạn biết chính xác icon nào vừa được bấm (Câu 1 hay Câu 2?).
                int itemId = menuItem.getItemId();
//                Giải thích: Nếu ID là navhome -> Cho trang Home vào khay.
//                        Nếu ID là navcau1 -> Cho trang Câu 1 vào khay... Rất dễ hiểu đúng không?
                if (itemId == R.id.navhome) {
//                    new HomeFragment(): Lệnh này tạo ra một "phiên bản" mới của trang Home.
                    selectedFragment = new HomeFragment();
                } else if (itemId == R.id.navcau1) {
                    selectedFragment = new Cau1Fragment();
                } else if (itemId == R.id.navcau2) {
                    selectedFragment = new Cau2Fragment();
                } else if (itemId == R.id.navcau3) {
                    selectedFragment = new Cau3Fragment();
                } else if (itemId == R.id.navcau4) {
                    selectedFragment = new Cau4Fragment();
                }

                if (selectedFragment != null) {
//                    getSupportFragmentManager()
//                    Giải thích: Đây là "Người quản lý" tất cả các mảnh ghép (Fragment) trong ứng dụng.
//                    Bắt đầu một "Phiên làm việc" hoặc một "Giao dịch".
    /*R.id.noihienthifragment: Là cái "khung ảnh" (vùng trống) bạn đã vẽ ở file XML.
selectedFragment: Là cái "ảnh" (trang mới) bạn muốn bỏ vào.
Nghĩa là: "Hãy gỡ cái trang cũ đang nằm ở khung này ra, và lắp cái trang selectedFragment mới vào cho tôi".
commit(), người quản lý mới bắt đầu thực hiện các lệnh đó.*/
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.noihienthifragment, selectedFragment)
                            .commit();
                }
                /*Giải thích: Trả về kết quả cho thanh menu.
Nghĩa là: "Tôi đã xử lý xong cái nút bấm này rồi, anh hãy tô màu/đậm cái icon đó lên để người dùng biết là họ đang ở trang đó nhé!".
Lưu ý: Nếu bạn return false, bạn vẫn chuyển được trang nhưng cái icon ở dưới sẽ không được sáng lên đâu.*/
                return true;
            }
        });
    }
}