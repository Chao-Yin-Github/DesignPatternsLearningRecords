package ObserverPattern;

import java.util.*;

public class board3 implements Display,Observer{
    private double humidity;
    Observable observable;

    public board3(Observable observable){
        this.observable = observable;
        observable.addObserver(this);
    }
    public void update (Observable o,Object object){
        if(o instanceof WeatherStation){
            WeatherStation weatherStation = (WeatherStation) o;
            this.humidity=weatherStation.getHumidity();
            display();
        }
    }

    private void display() {
        System.out.println("board3 see something strange: "+humidity);
    }
}
