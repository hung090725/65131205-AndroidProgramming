package hung.edu.css;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RssAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ProgressBar progressBar;
    private TextView tvEmpty;
    private TextView tvResultCount;
    private ChipGroup chipGroup;

    private List<RssItem> rssItems = new ArrayList<>();
    private String currentRssUrl = FetchRssTask.RSS_URLS[0];  // Mặc định: Thế giới
    private int currentSourceIndex = 0;
    private boolean showBookmarkedOnly = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Thiết lập Toolbar làm ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Khởi tạo views
        recyclerView   = findViewById(R.id.recyclerView);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        progressBar    = findViewById(R.id.progressBar);
        tvEmpty        = findViewById(R.id.tvEmpty);
        tvResultCount  = findViewById(R.id.tvResultCount);
        chipGroup      = findViewById(R.id.chipGroup);

        // Cấu hình RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RssAdapter(this, rssItems);
        recyclerView.setAdapter(adapter);

        // Cấu hình SwipeRefreshLayout
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
        swipeRefreshLayout.setOnRefreshListener(this::loadRssData);

        // Cấu hình ChipGroup — Tính năng 1: Filter bookmark
        setupChipGroup();

        // Tải dữ liệu lần đầu
        loadRssData();
    }

    // ===== CHIP GROUP (Filter: Tất cả / Đã lưu) =====
    private void setupChipGroup() {
        Chip chipAll = findViewById(R.id.chipAll);
        Chip chipBookmarked = findViewById(R.id.chipBookmarked);

        chipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            if (checkedIds.contains(R.id.chipBookmarked)) {
                showBookmarkedOnly = true;
                adapter.showBookmarkedOnly(true);
                updateResultCount();
            } else {
                showBookmarkedOnly = false;
                adapter.showBookmarkedOnly(false);
                updateResultCount();
            }
        });
    }

    // ===== TẢI DỮ LIỆU RSS =====
    private void loadRssData() {
        if (!swipeRefreshLayout.isRefreshing()) {
            progressBar.setVisibility(View.VISIBLE);
        }
        tvEmpty.setVisibility(View.GONE);
        tvResultCount.setVisibility(View.GONE);

        FetchRssTask fetchTask = new FetchRssTask();
        fetchTask.fetch(currentRssUrl, new FetchRssTask.RssCallback() {
            @Override
            public void onSuccess(List<RssItem> items) {
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);

                if (items.isEmpty()) {
                    tvEmpty.setVisibility(View.VISIBLE);
                    tvEmpty.setText(R.string.no_data);
                } else {
                    tvEmpty.setVisibility(View.GONE);
                    rssItems = items;
                    adapter.updateData(items);
                    // Giữ trạng thái filter bookmark nếu đang bật
                    if (showBookmarkedOnly) {
                        adapter.showBookmarkedOnly(true);
                    }
                    updateResultCount();
                }
            }

            @Override
            public void onError(String error) {
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
                tvEmpty.setVisibility(View.VISIBLE);
                tvEmpty.setText(R.string.loading_error);
                Toast.makeText(MainActivity.this,
                        getString(R.string.error_prefix) + error, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void updateResultCount() {
        int count = adapter.getItemCount();
        if (count > 0) {
            tvResultCount.setText(getString(R.string.result_count, count));
            tvResultCount.setVisibility(View.VISIBLE);
        } else {
            tvResultCount.setVisibility(View.GONE);
        }
    }

    // ===== MENU (Tìm kiếm + Chọn nguồn RSS) =====
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        // Tính năng 3: Search view
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        if (searchView != null) {
            searchView.setQueryHint(getString(R.string.search_hint));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    adapter.getFilter().filter(query, count -> updateResultCount());
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapter.getFilter().filter(newText, count -> updateResultCount());
                    return true;
                }
            });

            // Khi đóng search, reset count
            searchItem.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
                @Override
                public boolean onMenuItemActionExpand(MenuItem item) { return true; }

                @Override
                public boolean onMenuItemActionCollapse(MenuItem item) {
                    adapter.getFilter().filter("", count -> updateResultCount());
                    return true;
                }
            });
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_source) {
            // Tính năng 2: Chọn nguồn RSS
            showSourcePickerDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // ===== CHỌN NGUỒN RSS (Bottom-style dialog) =====
    private void showSourcePickerDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.choose_source)
                .setSingleChoiceItems(
                        FetchRssTask.RSS_NAMES,
                        currentSourceIndex,
                        (dialog, which) -> {
                            if (currentSourceIndex != which) {
                                currentSourceIndex = which;
                                currentRssUrl = FetchRssTask.RSS_URLS[which];
                                // Update toolbar title
                                if (getSupportActionBar() != null) {
                                    getSupportActionBar().setTitle(
                                            "VnExpress · " + FetchRssTask.RSS_NAMES[which]);
                                }
                                loadRssData();
                                Toast.makeText(this,
                                        getString(R.string.source_changed,
                                                FetchRssTask.RSS_NAMES[which]),
                                        Toast.LENGTH_SHORT).show();
                            }
                            dialog.dismiss();
                        })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }
}