package gk1.hung.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import gk1.hung.R;
import gk1.hung.models.Remedy;

public class RemedyAdapter extends RecyclerView.Adapter<RemedyAdapter.RemedyViewHolder> {

    private Context context;
    private List<Remedy> remedyList;

    public RemedyAdapter(Context context, List<Remedy> remedyList) {
        this.context = context;
        this.remedyList = remedyList;
    }

    @NonNull
    @Override
    public RemedyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_remedy, parent, false);
        return new RemedyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RemedyViewHolder holder, int position) {
        Remedy remedy = remedyList.get(position);
        
        holder.tvName.setText(remedy.getName());
        holder.tvTime.setText('Thời gian: ' + remedy.getTime());
        
        int resId = context.getResources().getIdentifier(remedy.getImage(), 'drawable', context.getPackageName());
        if (resId != 0) {
            holder.imgRemedy.setImageResource(resId);
        }
    }

    @Override
    public int getItemCount() {
        return remedyList.size();
    }

    public static class RemedyViewHolder extends RecyclerView.ViewHolder {
        ImageView imgRemedy;
        TextView tvName, tvTime;

        public RemedyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgRemedy = itemView.findViewById(R.id.imgRemedy);
            tvName = itemView.findViewById(R.id.tvRemedyName);
            tvTime = itemView.findViewById(R.id.tvRemedyTime);
        }
    }
}
