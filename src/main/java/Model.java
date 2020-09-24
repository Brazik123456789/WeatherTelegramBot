public class Model {

    private String name;
    private Double temp;
    private Double humidity;
    private int pressure;


    public String getName() {
        return name;
    }

    public Double getTemp() {
        return temp;
    }

    public Double getHumidity() {
        return humidity;
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

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public Model() {}
}
