package thigk2.nguyenvanhung;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter hien thi danh sach bai thuoc dung RecyclerView.
 */
public class BaiThuocAdapter extends RecyclerView.Adapter<BaiThuocAdapter.ViewHolder> {

    private List<BaiThuoc> dsBaiThuoc;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(BaiThuoc baiThuoc);
    }

    public BaiThuocAdapter(Context context, List<BaiThuoc> dsBaiThuoc, OnItemClickListener listener) {
        this.context = context;
        this.dsBaiThuoc = dsBaiThuoc;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bai_thuoc, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiThuoc baiThuoc = dsBaiThuoc.get(position);
        holder.txtTen.setText(baiThuoc.getTen());
        holder.txtThoiGian.setText("Thoi gian: " + baiThuoc.getThoiGian());
        
        holder.itemView.setOnClickListener(v -> listener.onItemClick(baiThuoc));
    }

    @Override
    public int getItemCount() {
        return dsBaiThuoc.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTen, txtThoiGian;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txtTenBaiThuoc);
            txtThoiGian = itemView.findViewById(R.id.txtThoiGian);
        }
    }
}
