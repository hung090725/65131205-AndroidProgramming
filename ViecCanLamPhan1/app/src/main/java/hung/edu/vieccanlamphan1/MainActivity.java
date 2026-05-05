package hung.edu.vieccanlamphan1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<TASKS> lstVCL;
    EditText edtName, edtDate, edtMessage, edtPriority;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 1. Ánh xạ View
        edtName = findViewById(R.id.edtName);
        edtDate = findViewById(R.id.edtDate);
        edtMessage = findViewById(R.id.edtMessage);
        edtPriority = findViewById(R.id.edtPriority);
        btnSave = findViewById(R.id.btnSave);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // 2. Kết nối Firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("TASKS");

        // 3. Code lắng nghe dữ liệu cũ
        lstVCL = new ArrayList<TASKS>();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                lstVCL.clear();
                for(DataSnapshot obj : snapshot.getChildren()){
                    TASKS vcl = obj.getValue(TASKS.class);
                    lstVCL.add(vcl);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });

        // 4. Code xử lý nút Lưu
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                String date = edtDate.getText().toString();
                String msg = edtMessage.getText().toString();
                String priority = edtPriority.getText().toString();

                if (name.isEmpty() || date.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập Tên và Ngày!", Toast.LENGTH_SHORT).show();
                    return;
                }

                TASKS task = new TASKS(name, date, msg, priority);
                
                databaseReference.push().setValue(task.toFireBaseOb())
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(MainActivity.this, "Đã lưu việc cần làm!", Toast.LENGTH_SHORT).show();
                        edtName.setText(""); edtDate.setText(""); edtMessage.setText(""); edtPriority.setText("");
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(MainActivity.this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    });
            }
        });
    }
}