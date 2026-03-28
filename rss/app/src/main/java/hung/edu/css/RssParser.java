package hung.edu.css;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RssParser {

    public List<RssItem> parse(InputStream inputStream) {
        List<RssItem> items = new ArrayList<>();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            // Cho phép namespace để đọc được các tag như media:content
            factory.setNamespaceAware(false);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(inputStream, "UTF-8");

            boolean insideItem = false;
            RssItem currentItem = null;
            String currentTag = "";

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        currentTag = parser.getName();
                        if ("item".equalsIgnoreCase(currentTag)) {
                            insideItem = true;
                            currentItem = new RssItem();
                        } else if (insideItem && currentItem != null) {
                            // Xử lý tag <enclosure> — ảnh thumbnail của VnExpress
                            if ("enclosure".equalsIgnoreCase(currentTag)) {
                                String url = parser.getAttributeValue(null, "url");
                                if (url != null && !url.isEmpty()) {
                                    currentItem.setImageUrl(url);
                                }
                            }
                        }
                        break;

                    case XmlPullParser.TEXT:
                        // XmlPullParser.TEXT trả về text thuần — CDATA cũng dùng event này
                        if (insideItem && currentItem != null && !currentTag.isEmpty()) {
                            String text = parser.getText();
                            if (text != null) {
                                text = text.trim();
                                if (!text.isEmpty()) {
                                    switch (currentTag) {
                                        case "title":
                                            // Tránh ghi đè title của <channel>
                                            if (currentItem.getTitle() == null) {
                                                currentItem.setTitle(text);
                                            }
                                            break;
                                        case "description":
                                            // VnExpress dùng CDATA bên trong description
                                            String desc = extractDescription(text);
                                            currentItem.setDescription(desc);
                                            // Nếu chưa có ảnh, thử trích xuất từ CDATA
                                            if (currentItem.getImageUrl() == null ||
                                                    currentItem.getImageUrl().isEmpty()) {
                                                String imgUrl = extractImageUrl(text);
                                                if (imgUrl != null) {
                                                    currentItem.setImageUrl(imgUrl);
                                                }
                                            }
                                            break;
                                        case "link":
                                            if (currentItem.getLink() == null) {
                                                currentItem.setLink(text);
                                            }
                                            break;
                                        case "pubDate":
                                            currentItem.setPubDate(formatDate(text));
                                            break;
                                        case "guid":
                                            currentItem.setGuid(text);
                                            break;
                                    }
                                }
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        String endTag = parser.getName();
                        if ("item".equalsIgnoreCase(endTag)) {
                            if (currentItem != null) {
                                // Nếu không có guid, dùng link thay thế
                                if (currentItem.getGuid() == null || currentItem.getGuid().isEmpty()) {
                                    currentItem.setGuid(currentItem.getLink());
                                }
                                items.add(currentItem);
                            }
                            insideItem = false;
                            currentItem = null;
                        }
                        // Reset currentTag khi kết thúc tag bên trong item
                        if (insideItem) {
                            currentTag = "";
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Trích xuất URL ảnh đầu tiên từ CDATA HTML.
     */
    private String extractImageUrl(String html) {
        // Tìm src="..." hoặc src='...'
        Pattern pattern = Pattern.compile("<img[^>]+src\\s*=\\s*[\"']([^\"']+)[\"']",
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(html);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    /**
     * Loại bỏ HTML tags từ CDATA, trả về text thuần.
     */
    private String extractDescription(String html) {
        // Xóa thẻ <a ...>...</a> chứa ảnh (link bọc ảnh)
        String text = html.replaceAll("<a[^>]*><img[^>]*/?>\\s*</a>", "");
        // Xóa tất cả HTML tags còn lại
        text = text.replaceAll("<[^>]*>", "");
        // Xóa ký tự thừa như </br> hay &nbsp;
        text = text.replaceAll("(?i)</br>|&nbsp;", " ");
        // Chuẩn hóa khoảng trắng
        text = text.replaceAll("\\s+", " ").trim();
        return text;
    }

    /**
     * Format ngày đăng từ RSS sang dạng dd/MM/yyyy HH:mm.
     */
    private String formatDate(String pubDate) {
        try {
            java.text.SimpleDateFormat inputFormat =
                    new java.text.SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", java.util.Locale.ENGLISH);
            java.text.SimpleDateFormat outputFormat =
                    new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault());
            java.util.Date date = inputFormat.parse(pubDate);
            if (date != null) {
                return outputFormat.format(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pubDate;
    }
}
