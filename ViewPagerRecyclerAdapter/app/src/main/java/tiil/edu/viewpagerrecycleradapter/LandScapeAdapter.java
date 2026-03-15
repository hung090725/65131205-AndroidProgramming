package tiil.edu.viewpagerrecycleradapter;

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

public class LandScapeAdapter extends RecyclerView.Adapter<LandScapeAdapter.ItemLandHolder> {
    Context context;
    ArrayList<LandScape>lstDaTa;

    public LandScapeAdapter(Context context, ArrayList<LandScape> lstDaTa) {
        this.context = context;
        this.lstDaTa = lstDaTa;
    }

    @NonNull
    @Override
    public ItemLandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater cai_bom = LayoutInflater.from(context);
        View vItem = cai_bom.inflate(R.layout.item_land,parent,false);
        ItemLandHolder viewholderCreated = new ItemLandHolder(vItem);

        return viewholderCreated;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLandHolder holder, int position) {
        // lấy đối tượng hiển thị
        LandScape landScapeHienThi = lstDaTa.get(position);
        //Trích Thông tin
        String capTion = landScapeHienThi.getLandCation();
        String tenFileAnh = landScapeHienThi.getLandImageFileName();
        // đặt vào các trường thông tin của holder hiển thị
        holder.tvCapTion.setText(capTion);
        // ĐẶt ảnh
        String packageName = holder.itemView.getContext().getPackageName();
        int imageId = holder.itemView.getResources().getIdentifier(tenFileAnh,"mipmap",packageName);
        holder.ivLandScape.setImageResource(imageId);
    }

    @Override
    public int getItemCount() {
        return lstDaTa.size();
    }

    class ItemLandHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvCapTion;
        ImageView ivLandScape;

//        viewholder
        public ItemLandHolder(@NonNull View itemView) {
            super(itemView);
            tvCapTion = itemView.findViewById(R.id.textViewCation);
            ivLandScape  = itemView.findViewById(R.id.imageViewLand);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
//            .. code ở đay
            int viTriDuocClick = getBindingAdapterPosition();
            LandScape landScapeDuocClick = lstDaTa.get(viTriDuocClick);
            // bốc thông tin
            String ten = landScapeDuocClick.getLandCation();
            String tenFile =landScapeDuocClick.getLandImageFileName();
            // toast Tên
            String chuoiThongBao = "Bạn đã click vào " + ten;
            Toast.makeText(v.getContext(),chuoiThongBao,Toast.LENGTH_LONG).show();
        }
    }
}
