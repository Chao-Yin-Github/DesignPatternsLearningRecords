import java.util.ArrayList;

public class WeatherStation implements DataCollector{
    ArrayList<Observer> observers = new ArrayList<>();
    double temperature,humidity,pressure;
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyAllObservers() {
        for(Observer observer :observers){
            observer.update(temperature,humidity,pressure);
        }
    }

    public void setInformation(double temperature,double humidity,double pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyAllObservers();
    }
}
