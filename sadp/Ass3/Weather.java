import java.io.IOException;
import java.nio.file.*;
import java.util.Observable;
import java.util.Observer;

class WeatherStation extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;

    public void setMeasurement(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        setChanged();
        notifyObservers();
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

    public void readWeatherData(String filePath) {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(filePath).getParent();
            
            if (path == null) {
                System.err.println("Invalid file path: " + filePath);
                return;
            }

            path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
            System.out.println("Monitoring file: " + filePath);

            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                        Path changed = (Path) event.context();
                        if (changed.endsWith(Paths.get(filePath).getFileName())) {
                            System.out.println("File changed: " + filePath);
                            updateWeatherData(filePath);
                        }
                    }
                }
                key.reset();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateWeatherData(String filePath) {
        try {
            String data = new String(Files.readAllBytes(Paths.get(filePath)));
            String[] values = data.split(",");
            float temperature = Float.parseFloat(values[0]);
            float humidity = Float.parseFloat(values[1]);
            float pressure = Float.parseFloat(values[2]);
            System.out.println("Updated weather data: " + temperature + ", " + humidity + ", " + pressure);
            setMeasurement(temperature, humidity, pressure);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class WeatherObserver implements Observer {
    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherStation) {
            WeatherStation weather = (WeatherStation) o;
            this.temperature = weather.getTemperature();
            this.humidity = weather.getHumidity();
            this.pressure = weather.getPressure();
            display();
        }
    }

    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity, Pressure: " + pressure + " atm");
    }
}

public class Weather {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        WeatherObserver weatherObserver = new WeatherObserver();
        weatherStation.addObserver(weatherObserver);

        // Path to the weather data file
        String filePath = "./weather_data.txt";
        if (!Files.exists(Paths.get(filePath))) {
            System.err.println("File does not exist: " + filePath);
            return;
        }

        // Print initial file data
        try {
            System.out.println("Initial file data: " + String.join(" ", Files.readAllLines(Paths.get(filePath))));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Start reading weather data from the file
        new Thread(() -> weatherStation.readWeatherData(filePath)).start();

        // Keep the main thread alive
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}