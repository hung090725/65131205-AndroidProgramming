package hung.edu.css;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Singleton quản lý bookmark bằng SharedPreferences.
 */
public class BookmarkManager {

    private static final String PREF_NAME = "rss_bookmarks";
    private static final String KEY_BOOKMARKS = "bookmarked_guids";

    private static BookmarkManager instance;
    private final SharedPreferences prefs;

    private BookmarkManager(Context context) {
        prefs = context.getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized BookmarkManager getInstance(Context context) {
        if (instance == null) {
            instance = new BookmarkManager(context);
        }
        return instance;
    }

    /**
     * Lưu bookmark cho bài viết (theo guid).
     */
    public void addBookmark(String guid) {
        if (guid == null || guid.isEmpty()) return;
        Set<String> bookmarks = getBookmarkSet();
        bookmarks.add(guid);
        prefs.edit().putStringSet(KEY_BOOKMARKS, bookmarks).apply();
    }

    /**
     * Xóa bookmark.
     */
    public void removeBookmark(String guid) {
        if (guid == null || guid.isEmpty()) return;
        Set<String> bookmarks = getBookmarkSet();
        bookmarks.remove(guid);
        prefs.edit().putStringSet(KEY_BOOKMARKS, bookmarks).apply();
    }

    /**
     * Toggle bookmark: đã lưu thì bỏ, chưa lưu thì thêm.
     * @return true nếu sau toggle là đã bookmark.
     */
    public boolean toggleBookmark(String guid) {
        if (isBookmarked(guid)) {
            removeBookmark(guid);
            return false;
        } else {
            addBookmark(guid);
            return true;
        }
    }

    /**
     * Kiểm tra bài viết có đang được bookmark không.
     */
    public boolean isBookmarked(String guid) {
        if (guid == null || guid.isEmpty()) return false;
        return getBookmarkSet().contains(guid);
    }

    /**
     * Lấy Set chứa tất cả guid đang được bookmark.
     */
    public Set<String> getBookmarkSet() {
        Set<String> saved = prefs.getStringSet(KEY_BOOKMARKS, null);
        if (saved == null) return new HashSet<>();
        return new HashSet<>(saved); // trả về bản copy để tránh lỗi ConcurrentModification
    }
}
