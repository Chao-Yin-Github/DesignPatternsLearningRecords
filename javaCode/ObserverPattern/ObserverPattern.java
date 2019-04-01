package ObserverPattern;

public class ObserverPattern {
    public static void main(String[] args){
        WeatherStation weatherStation = new WeatherStation();
        board1 observer1 = new board1(weatherStation);
        board2 observer2 = new board2(weatherStation);
        board3 observer3 = new board3(weatherStation);

        weatherStation.setInformation(89,99,100);
        weatherStation.setInformation(10,6,0);
        weatherStation.deleteObserver(observer3);
        weatherStation.setInformation(35,36,37);
    }
}
