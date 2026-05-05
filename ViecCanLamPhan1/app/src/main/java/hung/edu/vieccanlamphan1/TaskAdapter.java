package hung.edu.vieccanlamphan1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private List<TASKS> taskList;
    private Context context;

    public TaskAdapter(List<TASKS> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        TASKS task = taskList.get(position);
        holder.txtTaskName.setText(task.getName());
        holder.txtTaskDate.setText("Ngày: " + task.getDate());
        holder.txtTaskMessage.setText(task.getMessage());
        holder.txtPriority.setText(task.getPriority());

        // Set priority color
        int color;
        if (task.getPriority() != null) {
            switch (task.getPriority()) {
                case "Cao":
                    color = ContextCompat.getColor(context, R.color.priority_high);
                    break;
                case "Thấp":
                    color = ContextCompat.getColor(context, R.color.priority_low);
                    break;
                default:
                    color = ContextCompat.getColor(context, R.color.priority_medium);
                    break;
            }
        } else {
            color = ContextCompat.getColor(context, R.color.priority_medium);
        }
        
        holder.viewPriority.setBackgroundColor(color);
        holder.txtPriority.setTextColor(color);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView txtTaskName, txtTaskDate, txtTaskMessage, txtPriority;
        View viewPriority;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTaskName = itemView.findViewById(R.id.txtTaskName);
            txtTaskDate = itemView.findViewById(R.id.txtTaskDate);
            txtTaskMessage = itemView.findViewById(R.id.txtTaskMessage);
            txtPriority = itemView.findViewById(R.id.txtPriority);
            viewPriority = itemView.findViewById(R.id.viewPriority);
        }
    }
}
