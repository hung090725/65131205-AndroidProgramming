package tiil.edu.chuyentrangintent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

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

    }
    public void ChuyenSangMH2(View v){
        // 1.tạo đối tượng Intent (Thu)
        // tham số 1 là hiện tại 2 là gửi tới(.class)
        Intent ThuKichHoatMH2 = new Intent(MainActivity.this, // MH hiện  tại
                                                    MainActivity2.class); // màng hình chuyển tới

       // 1+ gói dữ liệu sẽ có các bước con bên trong
        // tìm điều khiển chứa dữ liệu
        EditText  edtHoTen = findViewById(R.id.edtHoVaTenMH1);
        EditText  edtTuoi = findViewById(R.id.edtNumber);
        //1.2 lấy dữ liệu ra từ các điều khiển tương ứng
        String HoTen = edtHoTen.getText().toString();
        String Tuoi = edtTuoi.getText().toString();
        // 1.3 Gói vào thư mỗi dữ liệu ta đặt  cho một cái key/ name để bên kia bóc ra
        // putExtra thêm thu vào tham số 1 từ  khóa để liên kết bên kia , tham số 2 là giá trị đc lưu trữ nội dung thu

        ThuKichHoatMH2.putExtra("tete",HoTen);
        ThuKichHoatMH2.putExtra("tuoi",Tuoi);



        // 2.Gửi Thư , mà không đợi phản hồi
        startActivity(ThuKichHoatMH2);
    }
}