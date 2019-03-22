public class board2 implements Observer,Displayer{
    double temperature,humidity,pressure;
    private DataCollector weatherCollector;

    public board2(DataCollector dataCollector){
        weatherCollector = dataCollector;
        weatherCollector.registerObserver(this);
    }

    @Override
    public void update(double temperature, double humidity, double pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        display();
    }

    public void display() {
        double max=temperature>humidity?(temperature>pressure?temperature:pressure):humidity>pressure?humidity:pressure;
        double min=temperature<humidity?(temperature<pressure?temperature:pressure):humidity<pressure?humidity:pressure;
        System.out.println("\nboard2:\nmax:"+max+"\nmin:"+min);
    }
}
