package hung.edu.chbaithi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class LandScapeAdapter extends RecyclerView.Adapter<LandScapeAdapter.LandScapeViewHolder> {
    /*Biến 1: private Context context;
Context là gì?
Context = "Môi trường" của ứng dụng. Nó chứa mọi thông tin về thiết bị (màn hình, bộ nhớ, tài nguyên...).
Tại sao Adapter cần nó?
Dòng LayoutInflater.from(context).inflate(...) dùng để nạp file XML lên màn hình. Không có context → không nạp được giao diện → ứng dụng văng (crash) ngay.

LandScape.java (Model) chỉ là cái khuôn để tạo ra một cảnh đẹp. Nó không tự biết "mình cùng với ai".
ArrayList<LandScape> mới là danh sách cụ thể (ví dụ: 5 cảnh đẹp bạn thêm vào trong Cau2Fragment).
Adapter cần giữ danh sách này để hàm getItemCount() biết được "có bao nhiêu dòng cần hiện"
và hàm onBindViewHolder() biết "dòng số 3 thì lấy dữ liệu ở đâu".*/
    private Context context;
    private ArrayList<LandScape> listData;

    public LandScapeAdapter(Context context, ArrayList<LandScape> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    /*Android gọi hàm này mỗi khi cần tạo thêm một dòng mới trong danh sách.
LayoutInflater.from(context) → Dùng context (môi trường) để khởi động máy nạp file XML.
.inflate(R.layout.item_landscape, ...) → Nạp file item_landscape.xml (cái khuôn bạn thiết kế ở Bước 2) vào thành một màn hình thật.
return new LandScapeViewHolder(view) → Bọc cái màn hình vừa nạp vào trong ViewHolder (giải thích ở dưới).*/
    public LandScapeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_landscape, parent, false);
        return new LandScapeViewHolder(view);
    }

    @Override
    //Locale = Ngôn ngữ/Vùng miền.
    //getDefault() = "Lấy ngôn ngữ đang dùng trên máy này".
    //Tại sao cần? Vì dấu phân cách thập phân khác nhau theo nước:
    //🇺🇸 Mỹ → 4.8 (dùng dấu chấm)
    //🇩🇪 Đức → 4,8 (dùng dấu phẩy)
    //Locale.getDefault() đảm bảo số hiện đúng theo máy của người dùng.
    //holder.tvRating → Chỉ vào cái ô TextView trên màn hình.
    //.setText(...) → Ra lệnh: "Hãy hiện đoạn chữ này lên màn hình".
    //Phần ... bên trong là "đoạn chữ" cần hiện. Vì nó là số nên không thể truyền thẳng vào được, phải "biến đổi" nó thành chữ trước.
    //Lớp bên trong - String.format(...):
    //String.format(Locale.getDefault(), "⭐ %.1f", landscape.getLandscapeRating())
    //Đây là một công cụ có sẵn của Java (không phải tự viết). Nó giống như một cái khuôn bánh - bạn chọn khuôn hình gì, đổ nguyên liệu vào, sẽ ra bánh đúng hình đó.
    //RecyclerView đang vẽ dòng số 1 → position = 1
    //
    //listData.get(1) → Lấy phiếu số 1 ra → Hội An
    //
    //→ landscape = thông tin của Hội An
    //   (landscapeName = "Phố Cổ Hội An")
    //   (landscapeLocation = "Quảng Nam")
    //   (landscapeRating = 4.7f)
    //   (landscapeImage = R.drawable.hoi_an)
    public void onBindViewHolder(@NonNull LandScapeViewHolder holder, int position) {
        LandScape landscape = listData.get(position);
        
        holder.tvName.setText(landscape.getLandscapeName());
        holder.tvLocation.setText("📍 " + landscape.getLandscapeLocation());
        holder.tvRating.setText(String.format(Locale.getDefault(), "⭐ %.1f", landscape.getLandscapeRating()));
        holder.ivImage.setImageResource(landscape.getLandscapeImage());
    }
/*Đơn giản nhất: Hàm này trả về số lượng cảnh đẹp trong danh sách. RecyclerView hỏi "Tôi cần hiện bao nhiêu dòng?" → Hàm này trả lời. */
    @Override
    public int getItemCount() {
        return listData.size();
    }

    public static class LandScapeViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvName;
        TextView tvLocation;
        TextView tvRating;

        public LandScapeViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.ivLandscape);
            tvName = itemView.findViewById(R.id.tvLandscapeName);
            tvLocation = itemView.findViewById(R.id.tvLandscapeLocation);
            tvRating = itemView.findViewById(R.id.tvLandscapeRating);
        }
    }
}
