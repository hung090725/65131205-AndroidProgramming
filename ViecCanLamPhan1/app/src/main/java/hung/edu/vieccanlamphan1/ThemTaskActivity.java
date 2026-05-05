package hung.edu.vieccanlamphan1;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class ThemTaskActivity extends AppCompatActivity {

    private TextInputEditText edtName, edtDate, edtMessage;
    private AutoCompleteTextView autoPriority;
    private MaterialButton btnSave;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_task);

        // Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Thêm Công Việc Mới");
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        // Initialize Views
        edtName = findViewById(R.id.edtName);
        edtDate = findViewById(R.id.edtDate);
        edtMessage = findViewById(R.id.edtMessage);
        autoPriority = findViewById(R.id.autoPriority);
        btnSave = findViewById(R.id.btnSave);

        // Setup Priority Dropdown
        String[] priorities = new String[]{"Cao", "Trung bình", "Thấp"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, priorities);
        autoPriority.setAdapter(adapter);
        autoPriority.setText(priorities[1], false); // Default to "Trung bình"

        // Setup Date Picker
        edtDate.setOnClickListener(v -> showDatePicker());

        // Firebase Reference
        databaseReference = FirebaseDatabase.getInstance().getReference("TASKS");

        // Save Button Logic
        btnSave.setOnClickListener(v -> saveTask());
    }

    private void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    String selectedDate = String.format("%02d/%02d/%d", dayOfMonth, (monthOfYear + 1), year1);
                    edtDate.setText(selectedDate);
                }, year, month, day);
        datePickerDialog.show();
    }

    private void saveTask() {
        String name = edtName.getText().toString().trim();
        String date = edtDate.getText().toString().trim();
        String msg = edtMessage.getText().toString().trim();
        String priority = autoPriority.getText().toString().trim();

        if (name.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đủ Tên và Ngày!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Disable button to prevent double clicks
        btnSave.setEnabled(false);

        TASKS task = new TASKS(name, date, msg, priority);
        
        databaseReference.push().setValue(task.toFireBaseOb())
            .addOnSuccessListener(aVoid -> {
                Toast.makeText(ThemTaskActivity.this, "Đã thêm công việc thành công!", Toast.LENGTH_SHORT).show();
                finish(); // Go back to list
            })
            .addOnFailureListener(e -> {
                btnSave.setEnabled(true);
                Toast.makeText(ThemTaskActivity.this, "Lỗi: " + e.getMessage(), Toast.LENGTH_LONG).show();
            });
    }
}
