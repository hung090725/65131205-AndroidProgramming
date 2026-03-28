package hung.edu.css;

import android.os.Handler;
import android.os.Looper;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Tải RSS feed bất đồng bộ và parse kết quả.
 * Hỗ trợ tùy chỉnh URL nguồn (đa chuyên mục VnExpress).
 */
public class FetchRssTask {

    public interface RssCallback {
        void onSuccess(List<RssItem> items);
        void onError(String error);
    }

    // Các nguồn RSS của VnExpress
    public static final String[] RSS_NAMES = {
            "Thế giới",
            "Thời sự",
            "Kinh doanh",
            "Khoa học",
            "Giải trí",
            "Thể thao"
    };

    public static final String[] RSS_URLS = {
            "https://vnexpress.net/rss/the-gioi.rss",
            "https://vnexpress.net/rss/thoi-su.rss",
            "https://vnexpress.net/rss/kinh-doanh.rss",
            "https://vnexpress.net/rss/khoa-hoc.rss",
            "https://vnexpress.net/rss/giai-tri.rss",
            "https://vnexpress.net/rss/the-thao.rss"
    };

    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    public void fetch(String rssUrl, RssCallback callback) {
        executor.execute(() -> {
            HttpURLConnection connection = null;
            try {
                URL url = new URL(rssUrl);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(15000);
                connection.setReadTimeout(15000);
                connection.setRequestProperty("User-Agent", "Mozilla/5.0");
                connection.connect();

                int responseCode = connection.getResponseCode();
                if (responseCode != HttpURLConnection.HTTP_OK) {
                    final String err = "HTTP " + responseCode;
                    handler.post(() -> callback.onError(err));
                    return;
                }

                InputStream inputStream = connection.getInputStream();
                RssParser parser = new RssParser();
                List<RssItem> items = parser.parse(inputStream);
                inputStream.close();

                handler.post(() -> callback.onSuccess(items));
            } catch (Exception e) {
                e.printStackTrace();
                final String msg = e.getMessage() != null ? e.getMessage() : "Lỗi không xác định";
                handler.post(() -> callback.onError(msg));
            } finally {
                if (connection != null) connection.disconnect();
            }
        });
    }
}
