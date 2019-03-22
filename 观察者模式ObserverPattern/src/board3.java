public class board3 implements Displayer,Observer{
    double temperature,humidity,pressure;
    private DataCollector weatherCollector;

    public board3(DataCollector dataCollector){
        weatherCollector = dataCollector;
        weatherCollector.registerObserver(this);
    }

    @Override
    public void display() {
        System.out.println("\nboard3:\n"+(temperature+humidity+pressure)/3+"\n------------------");
    }

    @Override
    public void update(double temperature, double humidity, double pressure) {
        this.humidity = humidity;
        this.pressure = pressure;
        this.temperature = temperature;
        display();
    }
}
