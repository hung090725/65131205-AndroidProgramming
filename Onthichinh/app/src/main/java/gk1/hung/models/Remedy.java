package gk1.hung.models;

public class Remedy {
    private String name;
    private String time;
    private String image;

    public Remedy(String name, String time, String image) {
        this.name = name;
        this.time = time;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getImage() {
        return image;
    }
}
