package gk1.hung;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import gk1.hung.adapters.FoodAdapter;
import gk1.hung.models.Food;

public class FoodActivity extends AppCompatActivity {

    private ListView lvFood;
    private FoodAdapter adapter;
    private List<Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodactivity);

        lvFood = findViewById(R.id.lvFood);
        foodList = new ArrayList<>();

        // 1. Đọc dữ liệu
        readJSON();

        // 2. Thiết lập Adapter
        adapter = new FoodAdapter(this, R.layout.item_food, foodList);
        lvFood.setAdapter(adapter);

        // 3. Sự kiện Click
        lvFood.setOnItemClickListener((parent, view, position, id) -> {
            Food selectedFood = foodList.get(position);
            Toast.makeText(this, "Bạn đã chọn: " + selectedFood.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    private void readJSON() {
        try {
            InputStream is = getAssets().open("foods.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            String jsonString = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(jsonString);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                foodList.add(new Food(
                        obj.getInt("id"),
                        obj.getString("name"),
                        obj.getString("description"),
                        obj.getString("image")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
