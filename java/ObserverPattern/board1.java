package ObserverPattern;

import java.util.*;

public class board1 implements Observer ,Display{
    private double temperature,humidity,pressure;
    Observable observable;

    public board1(Observable observable){
        this.observable = observable;
        observable.addObserver(this);
    }

    public void update(Observable observable,Object arg){
        if(observable instanceof WeatherStation){
            WeatherStation weatherStation = (WeatherStation)observable;
            this.temperature = weatherStation.getTemperature();
            this.humidity = weatherStation.getHumidity();
            this.pressure = weatherStation.getPressure();
            display();
        }
    }

    private void display() {
        System.out.println("ObserverPattern.board1:\n"+temperature+","+humidity+","+pressure);
    }
}
