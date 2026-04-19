package thigk2.nguyenvanhung;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * Adapter hien thi danh sach mon an.
 */
public class MonAnAdapter extends ArrayAdapter<MonAn> {
    private Context context;
    private int resource;
    private List<MonAn> dsMonAn;

    public MonAnAdapter(@NonNull Context context, int resource, @NonNull List<MonAn> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.dsMonAn = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(resource, parent, false);
        }

        MonAn monAn = dsMonAn.get(position);

        ImageView img = convertView.findViewById(R.id.imgMonAn);
        TextView txtTen = convertView.findViewById(R.id.txtTenMonAn);
        TextView txtMoTa = convertView.findViewById(R.id.txtMoTaNgan);

        txtTen.setText(monAn.getTen());
        txtMoTa.setText(monAn.getMoTa());
        
        // Tam thoi dung logo_app
        img.setImageResource(R.drawable.logo_app);

        return convertView;
    }
}
