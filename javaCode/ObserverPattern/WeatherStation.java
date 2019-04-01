package ObserverPattern;

import java.util.*;
public class WeatherStation extends Observable {
    private double temperature;
    private double humidity;
    private double pressure;


    public void setInformation(double temperature,double humidity,double pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        setChanged();
        notifyObservers();
    }

    public double getTemperature(){
        return temperature;
    }

    public double getHumidity(){
        return humidity;
    }

    public double getPressure(){
        return pressure;
    }
}
