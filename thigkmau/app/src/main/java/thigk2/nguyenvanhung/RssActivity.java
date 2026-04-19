package thigk2.nguyenvanhung;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Activity doc tin tuc suc khoe tu VNExpress RSS.
 * Code phong cach khong dau.
 */
public class RssActivity extends AppCompatActivity {

    private ListView lvRss;
    private ArrayList<String> dsTieuDe;
    private ArrayList<String> dsLienKet;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss);

        anhXa();
        taiDuLieuRss();
    }

    private void anhXa() {
        lvRss = findViewById(R.id.lvRss);
        dsTieuDe = new ArrayList<>();
        dsLienKet = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dsTieuDe);
        lvRss.setAdapter(adapter);

        findViewById(R.id.btnBackRss).setOnClickListener(v -> finish());

        lvRss.setOnItemClickListener((parent, view, position, id) -> {
            String url = dsLienKet.get(position);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });
    }

    private void taiDuLieuRss() {
        new Thread(() -> {
            try {
                URL url = new URL("https://vnexpress.net/rss/suc-khoe.rss");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                InputStream is = conn.getInputStream();

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(is, "UTF-8");

                String text = "";
                int eventType = parser.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    String tag = parser.getName();
                    if (eventType == XmlPullParser.START_TAG) {
                        // Bat dau the moi
                    } else if (eventType == XmlPullParser.TEXT) {
                        text = parser.getText();
                    } else if (eventType == XmlPullParser.END_TAG) {
                        if (tag.equalsIgnoreCase("title")) {
                            dsTieuDe.add(text);
                        } else if (tag.equalsIgnoreCase("link")) {
                            dsLienKet.add(text);
                        }
                    }
                    eventType = parser.next();
                }

                // Bo qua tieu de va link cua kenh chu
                if (dsTieuDe.size() > 0) dsTieuDe.remove(0);
                if (dsLienKet.size() > 0) dsLienKet.remove(0);

                runOnUiThread(() -> adapter.notifyDataSetChanged());

            } catch (Exception e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(RssActivity.this, "Loi tai tin tuc!", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}
