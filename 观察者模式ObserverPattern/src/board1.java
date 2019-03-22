public class board1 implements Observer,Displayer{
    double temperature,humidity,pressure;
    private DataCollector weatherData;

    public board1(DataCollector dataCollector){
        weatherData = dataCollector;
        weatherData.registerObserver(this);
    }

    @Override
    public void update(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    public void display() {
        System.out.println("board1:\ntemperature:"+temperature+"\nhumidity:"+humidity+"\npressure:"+pressure);
    }
}
