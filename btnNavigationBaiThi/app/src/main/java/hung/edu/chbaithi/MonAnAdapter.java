package hung.edu.chbaithi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Adapter danh sách Món Ăn.
 * Điểm đặc biệt: Khi nhấn vào một món → Dùng Intent chuyển sang ChiTietMonActivity
 */
public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.MonAnViewHolder> {

    private Context context;
    private ArrayList<MonAn> listData;

    public MonAnAdapter(Context context, ArrayList<MonAn> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public MonAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mon_an, parent, false);
        return new MonAnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonAnViewHolder holder, int position) {
        MonAn monAn = listData.get(position);

        holder.tvTen.setText(monAn.getTenMonAn());
        holder.tvMoTa.setText(monAn.getMoTa());
        holder.ivAnh.setImageResource(monAn.getHinhAnh());

        // Xử lý sự kiện khi nhấn vào một dòng
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ====== PHẦN QUAN TRỌNG: Dùng Intent chuyển màn hình ======
                
                // Bước 1: Tạo Intent - "Phong bì" chứa thông tin chuyển màn hình
                //   - context: Đang ở đâu (màn hình hiện tại)
                //   - ChiTietMonActivity.class: Muốn đến đâu (màn hình đích)
                Intent intent = new Intent(context, ChiTietMonActivity.class);

                // Bước 2: Bỏ dữ liệu vào phong bì (putExtra)
                //   - KEY: Tên "nhãn" của gói dữ liệu (phải trùng với bên nhận)
                //   - VALUE: Dữ liệu thực tế muốn gửi
                intent.putExtra(ChiTietMonActivity.KEY_TEN, monAn.getTenMonAn());
                intent.putExtra(ChiTietMonActivity.KEY_MO_TA, monAn.getMoTa());
                intent.putExtra(ChiTietMonActivity.KEY_HINH_ANH, monAn.getHinhAnh());

                // Bước 3: Gửi phong bì và chuyển màn hình
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class MonAnViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAnh;
        TextView tvTen;
        TextView tvMoTa;

        public MonAnViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAnh  = itemView.findViewById(R.id.ivMonAn);
            tvTen  = itemView.findViewById(R.id.tvTenMonAn);
            tvMoTa = itemView.findViewById(R.id.tvMoTaMonAn);
        }
    }
}
