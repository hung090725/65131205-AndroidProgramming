package thigk2.nguyenvanhung;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DuLichAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<DiaDiem> dsDiaDiem;

    public DuLichAdapter(Context context, int layout, List<DiaDiem> dsDiaDiem) {
        this.context = context;
        this.layout = layout;
        this.dsDiaDiem = dsDiaDiem;
    }

    @Override
    public int getCount() {
        return dsDiaDiem.size();
    }

    @Override
    public Object getItem(int position) {
        return dsDiaDiem.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Su dung ViewHolder de toi uu hieu nang
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
        }

        // Anh xa view trong item_du_lich.xml
        ImageView imgHinh = convertView.findViewById(R.id.img_dia_diem);
        TextView tvTen = convertView.findViewById(R.id.tv_ten_dia_diem);
        TextView tvDiaChi = convertView.findViewById(R.id.tv_dia_chi);

        // Gan gia tri
        DiaDiem diaDiem = dsDiaDiem.get(position);
        imgHinh.setImageResource(diaDiem.getHinhAnh());
        tvTen.setText(diaDiem.getTenDiaDiem());
        tvDiaChi.setText(diaDiem.getDiaChi());

        return convertView;
    }
}
