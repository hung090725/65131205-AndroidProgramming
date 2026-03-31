package hung.edu.fragmentdynamic;

import android.app.Activity;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

//                thêm Fragment
//        thay Fragment
//        xóa Fragment
//        tìm Fragment đang hiện

//        là để lấy ra đối tượng quản lý Fragment trong Activity.
//        lấy bộ quản lý Fragment
//        bắt đầu 1 lần thay đổi Fragment
//        đưa ContentFragment vào chỗ FrameContentHolder
//        đưa FooterFragment vào chỗ FrameFooterHolder
//        xác nhận để Android thực hiện
        FragmentManager  fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.FrameContentHolder,new ContentFragment())
                .add( R.id.FrameFooterHolder, new FooterFragment()).commit();

    }
}