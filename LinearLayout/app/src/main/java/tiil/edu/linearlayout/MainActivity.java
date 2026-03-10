package tiil.edu.linearlayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button button1;
    Button button2;
    Button button3;

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
        // 2. Ánh xạ Button từ XML sang Java
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        /*Này nút bấm, hãy thiết lập (set) một bộ phận lắng nghe (Listener)
        sự kiện khi có ai đó nhấn (Click) vào mày.
        "*/
        // 3. Xử lý sự kiện khi nút được nhấn
        // Toast dùng để tạo các thông báo nhỏ ở phía dưới màn hình.
//      hàm makeText ở trên mới chỉ "tạo" ra cái thông báo nhưng nó vẫn đang nằm trong bộ nhớ.
//      Để hiển thị thông báo ra ngoài màn hình thì Bạn phải gọi thêm lệnh .show()
//      thì nó mới thực sự "nhảy" ra ngoài màn hình cho bạn xem.
//        LENGTH_SHORT: Hiện khoảng 2 giây.
//        LENGTH_LONG: Hiện khoảng 3.5 giây.
        button1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Nút 1 được nhấn", Toast.LENGTH_SHORT).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Nút 2 được nhấn", Toast.LENGTH_SHORT).show();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Nút 3 được nhấn", Toast.LENGTH_SHORT).show();
            }
        });
    }
}