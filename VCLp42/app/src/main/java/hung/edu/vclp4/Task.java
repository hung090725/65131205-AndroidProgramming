package hung.edu.vclp4;

public class Task {
    private String id;
    private String name;
    private String content;
    private String priority;
    private String date;

    public Task() {
        // Cần thiết cho Firebase
    }

    public Task(String id, String name, String content, String priority, String date) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.priority = priority;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
