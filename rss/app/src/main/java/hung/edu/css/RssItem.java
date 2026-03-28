package hung.edu.css;

public class RssItem {
    private String title;
    private String description;
    private String link;
    private String pubDate;
    private String imageUrl;
    private String guid;
    private boolean bookmarked;

    public RssItem() {}

    public RssItem(String title, String description, String link, String pubDate, String imageUrl, String guid) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = pubDate;
        this.imageUrl = imageUrl;
        this.guid = guid;
        this.bookmarked = false;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public String getPubDate() { return pubDate; }
    public void setPubDate(String pubDate) { this.pubDate = pubDate; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getGuid() { return guid; }
    public void setGuid(String guid) { this.guid = guid; }

    public boolean isBookmarked() { return bookmarked; }
    public void setBookmarked(boolean bookmarked) { this.bookmarked = bookmarked; }
}
