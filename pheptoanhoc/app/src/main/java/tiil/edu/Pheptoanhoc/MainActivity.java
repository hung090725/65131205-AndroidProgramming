package tiil.edu.Pheptoanhoc;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText so1;
    EditText so2;
    EditText ketqua1;
    Button btncong;
    Button btntru;
    Button btnnhan;
    Button btnchia;



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
        xulydieukhien();
    }
    public void xulydieukhien(){
        so1 = (EditText) findViewById(R.id.edtso1);
        so2 = (EditText)findViewById(R.id.edtso2);
        ketqua1 = (EditText) findViewById(R.id.ketqua);
        btncong = (Button) findViewById(R.id.btncong);
        btntru = (Button) findViewById(R.id.btntru);
        btnnhan = (Button) findViewById(R.id.btnnhan);
        btnchia = (Button) findViewById(R.id.btnchia);

    }
    // xu lý cộng
    public void xulycong(View view) {
//         EditText so1 = (EditText) findViewById(R.id.edtso1);
//         EditText so2 = (EditText)findViewById(R.id.edtso2);
        String layso1 = so1.getText().toString();
        String layso2 = so2.getText().toString();
        double a = Double.parseDouble(layso1);
        double b = Double.parseDouble(layso2);
        double ketqua = a + b;
//         EditText ketqua1 = (EditText) findViewById(R.id.ketqua);
        ketqua1.setText(String.valueOf(ketqua));

    }
    public void xulytru(View view) {
//        EditText so1 = (EditText) findViewById(R.id.edtso1);
//        EditText so2 = (EditText)findViewById(R.id.edtso2);
        String layso1 = so1.getText().toString();
        String layso2 = so2.getText().toString();
        double a = Double.parseDouble(layso1);
        double b = Double.parseDouble(layso2);
        double ketqua = a - b;
//        EditText ketqua1 = (EditText) findViewById(R.id.ketqua);
        ketqua1.setText(String.valueOf(ketqua));


    }
    public void xulynhan(View view) {
//        EditText so1 = (EditText) findViewById(R.id.edtso1);
//        EditText so2 = (EditText)findViewById(R.id.edtso2);
        String layso1 = so1.getText().toString();
        String layso2 = so2.getText().toString();
        double a = Double.parseDouble(layso1);
        double b = Double.parseDouble(layso2);
        double ketqua = a * b;
//        EditText ketqua1 = (EditText) findViewById(R.id.ketqua);
        ketqua1.setText(String.valueOf(ketqua));


    }
    public void xulychia(View view){
//        EditText so1 = (EditText) findViewById(R.id.edtso1);
//        EditText so2 = (EditText)findViewById(R.id.edtso2);
        String layso1 = so1.getText().toString();
        String layso2 = so2.getText().toString();
        double a = Double.parseDouble(layso1);
        double b = Double.parseDouble(layso2);
        double ketqua = a / b;
//        EditText ketqua1 = (EditText) findViewById(R.id.ketqua);
        ketqua1.setText(String.valueOf(ketqua));


    }
}