// Target interface (BeatModel)
interface BeatModel {
    void initialize();
    void on();
    void off();
    void setBPM(int bpm);
}

// Adaptee interface (HeartModel)
interface HeartModel {
    int getHeartRate();
    void setHeartRate(int heartRate);
}

// Concrete Adaptee (HeartModelImpl)
class HeartModelImpl implements HeartModel {
    private int heartRate = 100;

    @Override
    public int getHeartRate() {
        return heartRate;
    }

    @Override
    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }
}

// Adapter (HeartAdapter)
class HeartAdapter implements BeatModel {
    private HeartModel heartModel;

    public HeartAdapter(HeartModel heartModel) {
        this.heartModel = heartModel;
    }

    @Override
    public void initialize() {
        System.out.println("Initializing Heart Adapter...");
    }

    @Override
    public void on() {
        System.out.println("Heart is on...");
    }

    @Override
    public void off() {
        System.out.println("Heart is off...");
    }

    @Override
    public void setBPM(int bpm) {
        heartModel.setHeartRate(bpm);
    }
}

// Client code
public class Heart {
    public static void main(String[] args) {
        HeartModel heartModel = new HeartModelImpl();
        BeatModel beatModel = new HeartAdapter(heartModel);

        beatModel.initialize();
        beatModel.on();
        beatModel.setBPM(120);
        System.out.println("Heart rate: " + heartModel.getHeartRate());
        beatModel.off();
    }
}
