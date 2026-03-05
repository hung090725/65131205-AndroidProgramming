package tiil.edu.appcong;

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
    //đây l;à sự kiện lắng nghe và xử lý sự kiện tính tổng
    // nhớ muốn tính tổng phải lấy dữ liệu từ edtA và edtB lấy điều khiển đó  dùng trên xml dùng findViewbyid(R.id.ten id)

    public void xulycong(View view){
    // tìm tham chiếu đến điều khiển của 2 ôn edittext nhớ làm phải có biên lưu lại nó thuộc kiểu editText nên trả về đó luôn nha
       EditText soA = findViewById(R.id.edtA);
       EditText soB = findViewById(R.id.edtB);
       EditText soKQ = findViewById(R.id.edtKQ);
       /*2. .getText() (Lấy nội dung)
Lệnh này dùng để "truy cập" vào bên trong ô nhập liệu để xem người dùng đã gõ cái gì.
Kết quả trả về của getText() không phải là chữ bình thường (String), mà là một kiểu dữ liệu đặc biệt của Android gọi là Editable. Kiểu này cho phép chỉnh sửa trực tiếp nhưng không thể đem đi tính toán ngay được.*/
       // trên mới là tìm dữ liệu thôi chưa lấy dưex liệu lấy ở editText thì dùng tenbien.getText().toString()
        // vì trả về kiểu chuỗi nên dùng kiểu trả về là chuỗi
        String laySoA= soA.getText().toString();
        String laySoB= soB.getText().toString();
        //tính tổng tính phải chuyển sang số r mới tính
        int chuyenSoA = Integer.parseInt(laySoA);
        int chuyenSoB = Integer.parseInt(laySoB);
        int tong = chuyenSoA + chuyenSoB;
        // setText gán ngược lại cho nó mà settext cũng dạng chuỗi nó là
        soKQ.setText(String.valueOf(tong));










    }
}