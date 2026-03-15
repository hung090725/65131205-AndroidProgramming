package tiil.edu.viewpager2tablayoutfragment;

public class QuocGia {
    private String countryName;
    private String countryFlag;
    private String population;

    public QuocGia(String countryName, String countryFlag, String population) {
        this.countryName = countryName;
        this.countryFlag = countryFlag;
        this.population = population;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(String countryFlag) {
        this.countryFlag = countryFlag;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

}
