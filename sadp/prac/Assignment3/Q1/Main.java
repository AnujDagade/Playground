import java.util.Observable;
import java.util.Observer;

class WeatherStation extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    public void measurementsChanged() {
        setChanged(); 
        notifyObservers(); 
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged(); 
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}

class WeatherObserver implements Observer {
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherObserver(Observable observable) {
        observable.addObserver(this); 
    }

    @Override
    public void update(Observable obs, Object arg) {
        if (obs instanceof WeatherStation) {
            WeatherStation weatherStation = (WeatherStation) obs;
            this.temperature = weatherStation.getTemperature();
            this.humidity = weatherStation.getHumidity();
            this.pressure = weatherStation.getPressure();
            display();
        }
    }

    public void display() {
        System.out.println("Current conditions: " + temperature + "°C, " +
                           humidity + "% humidity, " +
                           pressure + " hPa pressure.");
    }
}

public class Main {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();

        WeatherObserver currentConditionsDisplay = new WeatherObserver(weatherStation);

        weatherStation.setMeasurements(25.0f, 65.0f, 1013.0f);

        weatherStation.setMeasurements(28.0f, 70.0f, 1012.5f);

        weatherStation.setMeasurements(22.5f, 60.0f, 1011.8f);
    }
}
