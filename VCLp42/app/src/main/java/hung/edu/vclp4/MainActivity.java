package hung.edu.vclp4;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvTasks;
    private FloatingActionButton fabAddTask;
    private ImageButton btnLogout;
    
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    private List<Task> taskList;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        
        // Nếu chưa đăng nhập thì chuyển về LoginActivity
        if (user == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
            return;
        }

        // Kết nối tới nút "CongViec" trên Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference("CongViec");

        rvTasks = findViewById(R.id.rvTasks);
        fabAddTask = findViewById(R.id.fabAddTask);
        btnLogout = findViewById(R.id.btnLogout);

        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(taskList);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        rvTasks.setAdapter(taskAdapter);

        // Lấy dữ liệu từ Firebase
        fetchTasks();

        fabAddTask.setOnClickListener(v -> showAddTaskDialog());

        // Xử lý nút Đăng xuất
        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void fetchTasks() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                taskList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Task task = dataSnapshot.getValue(Task.class);
                    if (task != null) {
                        taskList.add(task);
                    }
                }
                taskAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Lỗi tải dữ liệu: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showAddTaskDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_task, null);
        builder.setView(view);

        EditText edtName = view.findViewById(R.id.edtTaskName);
        EditText edtContent = view.findViewById(R.id.edtTaskContent);
        EditText edtPriority = view.findViewById(R.id.edtTaskPriority);
        EditText edtDate = view.findViewById(R.id.edtTaskDate);

        builder.setPositiveButton("Thêm", (dialog, which) -> {
            String name = edtName.getText().toString().trim();
            String content = edtContent.getText().toString().trim();
            String priority = edtPriority.getText().toString().trim();
            String date = edtDate.getText().toString().trim();

            if (!TextUtils.isEmpty(name)) {
                addTaskToFirebase(name, content, priority, date);
            } else {
                Toast.makeText(this, "Tên công việc không được để trống", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

        builder.create().show();
    }

    private void addTaskToFirebase(String name, String content, String priority, String date) {
        String id = mDatabase.push().getKey();
        Task task = new Task(id, name, content, priority, date);
        if (id != null) {
            mDatabase.child(id).setValue(task).addOnCompleteListener(t -> {
                if (t.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Đã thêm công việc!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Lỗi: " + t.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}