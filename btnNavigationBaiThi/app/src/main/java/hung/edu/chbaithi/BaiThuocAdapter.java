package hung.edu.chbaithi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Adapter: Kết nối danh sách BaiThuoc với giao diện RecyclerView
 */
public class BaiThuocAdapter extends RecyclerView.Adapter<BaiThuocAdapter.BaiThuocViewHolder> {

    private Context context;
    private ArrayList<BaiThuoc> listData;

    // Constructor: Nhận vào môi trường và danh sách dữ liệu
    public BaiThuocAdapter(Context context, ArrayList<BaiThuoc> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public BaiThuocViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Nạp file item_bai_thuoc.xml vào làm khuôn cho mỗi dòng
        View view = LayoutInflater.from(context).inflate(R.layout.item_bai_thuoc, parent, false);
        return new BaiThuocViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaiThuocViewHolder holder, int position) {
        // Lấy dữ liệu tại vị trí position
        BaiThuoc baiThuoc = listData.get(position);

        // Gán dữ liệu vào các View
        holder.tvTen.setText(baiThuoc.getTenBaiThuoc());
        holder.tvCongDung.setText(baiThuoc.getCongDung());
        holder.ivAnh.setImageResource(baiThuoc.getHinhAnh());

        // Xử lý sự kiện khi nhấn vào một dòng → Hiện Toast thông báo
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khi nhấn, hiện Toast với tên bài thuốc được chọn
                Toast.makeText(context,
                        "Bạn đã chọn: " + baiThuoc.getTenBaiThuoc(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    // ViewHolder: Lưu trữ các View để tái sử dụng, giúp RecyclerView chạy mượt
    public static class BaiThuocViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAnh;
        TextView tvTen;
        TextView tvCongDung;

        public BaiThuocViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAnh     = itemView.findViewById(R.id.ivBaiThuoc);
            tvTen     = itemView.findViewById(R.id.tvTenBaiThuoc);
            tvCongDung= itemView.findViewById(R.id.tvCongDung);
        }
    }
}
