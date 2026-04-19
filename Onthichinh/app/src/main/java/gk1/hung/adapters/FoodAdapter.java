package gk1.hung.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import gk1.hung.R;
import gk1.hung.models.Food;

public class FoodAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Food> foodList;

    public FoodAdapter(Context context, int layout, List<Food> foodList) {
        this.context = context;
        this.layout = layout;
        this.foodList = foodList;
    }

    @Override
    public int getCount() {
        return foodList.size();
    }

    @Override
    public Object getItem(int position) {
        return foodList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        TextView tvName = convertView.findViewById(R.id.tvFoodName);
        TextView tvDesc = convertView.findViewById(R.id.tvFoodDesc);
        ImageView imgFood = convertView.findViewById(R.id.imgFood);

        Food food = foodList.get(position);

        tvName.setText(food.getName());
        tvDesc.setText(food.getDescription());
        
        int resId = context.getResources().getIdentifier(food.getImage(), 'drawable', context.getPackageName());
        if (resId != 0) {
            imgFood.setImageResource(resId);
        }

        return convertView;
    }
}
