public class Main {

    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        board1 observer1 = new board1(weatherStation);
        board2 observer2 = new board2(weatherStation);
        board3 observer3 = new board3(weatherStation);

        weatherStation.setInformation(16,11,65);
        weatherStation.setInformation(20,30,40);
        weatherStation.setInformation(35,36,37);
    }
}
