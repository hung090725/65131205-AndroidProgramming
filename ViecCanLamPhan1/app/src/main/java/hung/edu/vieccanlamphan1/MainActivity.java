package hung.edu.vieccanlamphan1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvTasks;
    private TaskAdapter adapter;
    private List<TASKS> taskList;
    private FloatingActionButton fabAdd;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Initialize Views
        rvTasks = findViewById(R.id.rvTasks);
        fabAdd = findViewById(R.id.fabAdd);
        
        // Setup RecyclerView
        taskList = new ArrayList<>();
        adapter = new TaskAdapter(taskList, this);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        rvTasks.setAdapter(adapter);

        // Firebase Reference
        databaseReference = FirebaseDatabase.getInstance().getReference("TASKS");

        // Listen for Data
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                taskList.clear();
                for (DataSnapshot data : snapshot.getChildren()) {
                    TASKS task = data.getValue(TASKS.class);
                    if (task != null) {
                        taskList.add(task);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Lỗi tải dữ liệu: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // FAB Click
        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ThemTaskActivity.class);
            startActivity(intent);
        });
    }
}