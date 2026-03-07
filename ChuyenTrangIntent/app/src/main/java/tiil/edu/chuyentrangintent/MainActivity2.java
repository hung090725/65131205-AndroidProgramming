package tiil.edu.chuyentrangintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main2), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // tìm đối tượng gắn bộ lắng nghe lưu trữ nó ở Button vì tìm ở button
        Button BtnMH2 = findViewById(R.id.btnmh2);
        // gắn bộ lắng nghe
        BtnMH2.setOnClickListener(BoLangNGheChuyenMH1);

        //khi màng hình mở ra ta lấy Intent đã kích hoạt dùng getIntent tự hiểu lấy thư nào luôn
        Intent  ThuNhanDuoc = getIntent();
        // bóc dữ liệu ra
         String layraten = ThuNhanDuoc.getStringExtra("tete");
         String laytuoi = ThuNhanDuoc.getStringExtra("tuoi");
         // bóc xong phải đặt lên vị trí nó
         // tìm điều khiển chứa dữ liệu
        TextView  TVTen = findViewById(R.id.TVHoTen);
        TextView  TVTuoi = findViewById(R.id.TVTuoiMH2);
        //1.2 lấy dữ liệu ra từ các điều khiển tương ứng
        /*
         TVTen: Là cái "bảng" (đối tượng TextView) mà bạn đã tìm thấy ở dòng trên bằng findViewById.
         setText: Thực hiện việc ghi thông tin đó lên màn hình.
         . (dấu chấm): Nghĩa là bạn đang ra lệnh cho cái "bảng" đó làm một việc gì đó.
         setText(...): Là hành động bạn yêu cầu cái bảng thực hiện (hãy hiển thị chữ đi).
layraten: Là "nội dung" bạn muốn ghi lên. Ở đây là cái tên mà bạn vừa "bóc" ra từ lá thư Intent.*/
        TVTen.setText(layraten);
        TVTuoi.setText("Tuổi: "+ laytuoi);


    }

    View.OnClickListener BoLangNGheChuyenMH1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // 1.tạo đối tượng Intent (Thu)
            // tham số 1 là hiện tại 2 là gửi tới(.class)
            Intent ThuKichHoatMH1 = new Intent(MainActivity2.this, MainActivity.class);
            // 2.Gửi Thư , mà không đợi phản hồi
            startActivity(ThuKichHoatMH1);
        }
    };
}