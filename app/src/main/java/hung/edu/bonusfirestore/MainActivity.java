package hung.edu.bonusfirestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Màn hình chính: hiển thị danh sách công việc của user đang đăng nhập.
 * Dữ liệu lưu tại: Firestore > users/{uid}/tasks/{taskId}
 */
public class MainActivity extends AppCompatActivity implements TaskAdapter.OnTaskActionListener {

    private RecyclerView recyclerView;
    private TextView tvWelcome, tvEmpty;
    private LinearProgressIndicator progressBar;
    private FloatingActionButton fabAdd;
    private MaterialToolbar toolbar;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private String currentUserId;

    private TaskAdapter adapter;
    private List<Task> taskList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo Firebase
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        // Kiểm tra đăng nhập
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            goToLogin();
            return;
        }
        currentUserId = user.getUid();

        initViews();
        setupToolbar(user.getEmail());
        setupRecyclerView();
        setupListeners();
        loadTasks();
    }

    private void initViews() {
        recyclerView = findViewById(R.id.recyclerView);
        tvWelcome = findViewById(R.id.tvWelcome);
        tvEmpty = findViewById(R.id.tvEmpty);
        progressBar = findViewById(R.id.progressBar);
        fabAdd = findViewById(R.id.fabAdd);
        toolbar = findViewById(R.id.toolbar);
    }

    private void setupToolbar(String email) {
        tvWelcome.setText("Xin chào, " + email);

        toolbar.setOnMenuItemClickListener(item -> {
            if (item.getItemId() == R.id.action_logout) {
                doLogout();
                return true;
            }
            return false;
        });
    }

    private void setupRecyclerView() {
        taskList = new ArrayList<>();
        adapter = new TaskAdapter(taskList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setupListeners() {
        fabAdd.setOnClickListener(v -> showAddTaskDialog());
    }

    // ========================================
    //  CRUD Firestore
    // ========================================

    /**
     * READ: Tải danh sách công việc của user hiện tại
     */
    private void loadTasks() {
        setLoading(true);

        db.collection("users")
                .document(currentUserId)
                .collection("tasks")
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    setLoading(false);
                    taskList.clear();

                    for (DocumentSnapshot doc : querySnapshot.getDocuments()) {
                        Task task = doc.toObject(Task.class);
                        if (task != null) {
                            task.setId(doc.getId());
                            taskList.add(task);
                        }
                    }

                    adapter.notifyDataSetChanged();
                    updateEmptyState();
                })
                .addOnFailureListener(e -> {
                    setLoading(false);
                    Toast.makeText(this, "Lỗi tải dữ liệu: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    /**
     * CREATE: Hiện dialog thêm công việc mới
     */
    private void showAddTaskDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_task, null);
        TextInputEditText edtTitle = dialogView.findViewById(R.id.edtTaskTitle);
        TextInputEditText edtDescription = dialogView.findViewById(R.id.edtTaskDescription);

        new AlertDialog.Builder(this)
                .setView(dialogView)
                .setPositiveButton("Lưu", (dialog, which) -> {
                    String title = edtTitle.getText().toString().trim();
                    String description = edtDescription.getText().toString().trim();

                    if (title.isEmpty()) {
                        Toast.makeText(this, "Vui lòng nhập tiêu đề!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    saveTask(title, description);
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    /**
     * CREATE: Lưu công việc mới lên Firestore
     */
    private void saveTask(String title, String description) {
        // KIỂM TRA GIỚI HẠN GÓI MIỄN PHÍ (Professional Logic)
        if (taskList.size() >= 20) {
            new AlertDialog.Builder(this)
                    .setTitle("Hết lượt miễn phí")
                    .setMessage("Bạn đã đạt giới hạn 20 công việc của bản Miễn phí. Vui lòng nâng cấp lên bản Pro để không giới hạn!")
                    .setPositiveButton("Đã hiểu", null)
                    .show();
            return;
        }

        setLoading(true);

        Map<String, Object> taskData = new HashMap<>();
        taskData.put("title", title);
        taskData.put("description", description);
        taskData.put("completed", false);
        taskData.put("createdAt", System.currentTimeMillis());
        taskData.put("userId", currentUserId);

        db.collection("users")
                .document(currentUserId)
                .collection("tasks")
                .add(taskData)
                .addOnSuccessListener(documentReference -> {
                    setLoading(false);
                    Toast.makeText(this, "Đã thêm công việc!", Toast.LENGTH_SHORT).show();
                    loadTasks();
                })
                .addOnFailureListener(e -> {
                    setLoading(false);
                    Toast.makeText(this, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    /**
     * UPDATE: Cập nhật trạng thái hoàn thành
     */
    @Override
    public void onTaskChecked(Task task, boolean isChecked) {
        db.collection("users")
                .document(currentUserId)
                .collection("tasks")
                .document(task.getId())
                .update("completed", isChecked)
                .addOnSuccessListener(aVoid -> {
                    task.setCompleted(isChecked);
                    adapter.notifyDataSetChanged();
                    String status = isChecked ? "Đã hoàn thành!" : "Chưa hoàn thành";
                    Toast.makeText(this, status, Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e ->
                    Toast.makeText(this, "Lỗi cập nhật: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
    }

    /**
     * DELETE: Xóa công việc
     */
    @Override
    public void onTaskDeleted(Task task) {
        new AlertDialog.Builder(this)
                .setTitle("Xác nhận xóa")
                .setMessage("Bạn có chắc muốn xóa \"" + task.getTitle() + "\"?")
                .setPositiveButton("Xóa", (dialog, which) -> {
                    setLoading(true);
                    db.collection("users")
                            .document(currentUserId)
                            .collection("tasks")
                            .document(task.getId())
                            .delete()
                            .addOnSuccessListener(aVoid -> {
                                setLoading(false);
                                taskList.remove(task);
                                adapter.notifyDataSetChanged();
                                updateEmptyState();
                                Toast.makeText(this, "Đã xóa!", Toast.LENGTH_SHORT).show();
                            })
                            .addOnFailureListener(e -> {
                                setLoading(false);
                                Toast.makeText(this, "Lỗi xóa: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            });
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    // ========================================
    //  Helper Methods
    // ========================================

    private void doLogout() {
        new AlertDialog.Builder(this)
                .setTitle("Đăng xuất")
                .setMessage("Bạn có chắc muốn đăng xuất?")
                .setPositiveButton("Đăng xuất", (dialog, which) -> {
                    mAuth.signOut();
                    goToLogin();
                })
                .setNegativeButton("Hủy", null)
                .show();
    }

    private void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void setLoading(boolean isLoading) {
        progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
    }

    private void updateEmptyState() {
        if (taskList.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
