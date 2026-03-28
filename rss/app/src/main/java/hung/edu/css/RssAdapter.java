package hung.edu.css;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RssAdapter extends RecyclerView.Adapter<RssAdapter.RssViewHolder> implements Filterable {

    private List<RssItem> itemList;         // Danh sách đang hiển thị
    private List<RssItem> itemListFull;     // Toàn bộ danh sách (dành cho filter)
    private final Context context;
    private final BookmarkManager bookmarkManager;

    public RssAdapter(Context context, List<RssItem> itemList) {
        this.context = context;
        this.itemList = new ArrayList<>(itemList);
        this.itemListFull = new ArrayList<>(itemList);
        this.bookmarkManager = BookmarkManager.getInstance(context);
    }

    @NonNull
    @Override
    public RssViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rss, parent, false);
        return new RssViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RssViewHolder holder, int position) {
        RssItem item = itemList.get(position);

        holder.tvTitle.setText(item.getTitle());
        holder.tvDescription.setText(item.getDescription());
        holder.tvPubDate.setText(item.getPubDate());

        // Load ảnh thumbnail bằng Picasso — an toàn với null check
        if (item.getImageUrl() != null && !item.getImageUrl().isEmpty()) {
            Picasso.get()
                    .load(item.getImageUrl())
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_placeholder)
                    .into(holder.ivThumbnail);
        } else {
            holder.ivThumbnail.setImageResource(R.drawable.ic_placeholder);
        }

        // Cập nhật trạng thái bookmark icon
        updateBookmarkIcon(holder, item);

        // Click vào card → mở trình duyệt
        holder.itemView.setOnClickListener(v -> {
            if (item.getLink() != null && !item.getLink().isEmpty()) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
                    context.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Nút chia sẻ
        holder.btnShare.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, item.getTitle());
            shareIntent.putExtra(Intent.EXTRA_TEXT,
                    item.getTitle() + "\n" + item.getLink());
            context.startActivity(Intent.createChooser(shareIntent, "Chia sẻ bài viết"));
        });

        // Nút bookmark — toggle trạng thái
        holder.btnBookmark.setOnClickListener(v -> {
            String guid = item.getGuid();
            boolean nowBookmarked = bookmarkManager.toggleBookmark(guid);
            item.setBookmarked(nowBookmarked);
            updateBookmarkIcon(holder, item);
        });
    }

    private void updateBookmarkIcon(@NonNull RssViewHolder holder, RssItem item) {
        boolean saved = bookmarkManager.isBookmarked(item.getGuid());
        item.setBookmarked(saved);
        if (saved) {
            holder.btnBookmark.setImageResource(R.drawable.ic_bookmark_filled);
            holder.btnBookmark.setColorFilter(
                    ContextCompat.getColor(context, R.color.bookmark_active));
        } else {
            holder.btnBookmark.setImageResource(R.drawable.ic_bookmark);
            holder.btnBookmark.clearColorFilter();
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    /**
     * Cập nhật danh sách dữ liệu (sau khi fetch mới hoặc đổi nguồn).
     */
    public void updateData(List<RssItem> newItems) {
        this.itemList = new ArrayList<>(newItems);
        this.itemListFull = new ArrayList<>(newItems);
        notifyDataSetChanged();
    }

    /**
     * Lọc chỉ hiển thị bài đã bookmark.
     */
    public void showBookmarkedOnly(boolean show) {
        if (show) {
            List<RssItem> filtered = new ArrayList<>();
            for (RssItem item : itemListFull) {
                if (bookmarkManager.isBookmarked(item.getGuid())) {
                    filtered.add(item);
                }
            }
            itemList = filtered;
        } else {
            itemList = new ArrayList<>(itemListFull);
        }
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return rssFilter;
    }

    /**
     * Filter hỗ trợ tìm theo cả tiêu đề lẫn mô tả.
     */
    private final Filter rssFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<RssItem> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(itemListFull);
            } else {
                String pattern = constraint.toString().toLowerCase().trim();
                for (RssItem item : itemListFull) {
                    boolean titleMatch = item.getTitle() != null &&
                            item.getTitle().toLowerCase().contains(pattern);
                    boolean descMatch = item.getDescription() != null &&
                            item.getDescription().toLowerCase().contains(pattern);
                    if (titleMatch || descMatch) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            itemList = (List<RssItem>) results.values;
            notifyDataSetChanged();
        }
    };

    static class RssViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumbnail;
        TextView tvTitle;
        TextView tvDescription;
        TextView tvPubDate;
        ImageButton btnShare;
        ImageButton btnBookmark;

        public RssViewHolder(@NonNull View itemView) {
            super(itemView);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvPubDate = itemView.findViewById(R.id.tvPubDate);
            btnShare = itemView.findViewById(R.id.btnShare);
            btnBookmark = itemView.findViewById(R.id.btnBookmark);
        }
    }
}
