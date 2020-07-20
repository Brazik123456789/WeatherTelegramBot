public class Model {

    private String name;
    private Double temp;
    private Double humidity;
    private String main;


    public String getName() {
        return name;
    }

    public Double getTemp() {
        return temp;
    }

    public Double getHumidity() {
        return humidity;
    }

    public String getMain() {
        return main;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public void setMain(String main) {
        this.main = main;
    }
}
