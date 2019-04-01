package ObserverPattern;

import java.util.*;

public class board2 implements Observer,Display{
    private double temperature,humidity,pressure;
    Observable observable;

    public board2(Observable observable){
        this.observable = observable;
        observable.addObserver(this);
    }
    public void update (Observable observable,Object object){
        if(observable instanceof WeatherStation){
            WeatherStation weatherStation = (WeatherStation) observable;
            this.humidity = weatherStation.getHumidity();
            this.temperature = weatherStation.getTemperature();
            this.pressure = weatherStation.getPressure();
            display();
        }
    }

    public void display(){
        double max = temperature>pressure?(temperature>humidity?temperature:humidity):pressure>humidity?pressure:humidity;
        double min = temperature<pressure?(temperature<humidity?temperature:humidity):pressure<humidity?pressure:humidity;
        System.out.println("board2 says some different:"+"\nmax is :"+max+"\nmin:"+min);
    }
}
