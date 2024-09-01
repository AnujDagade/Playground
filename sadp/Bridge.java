interface Vehicle {
    void produce();
    void assemble();
}

class Car implements Vehicle {
    @Override
    public void produce() {
        System.out.println("Producing a car.");
    }

    @Override
    public void assemble() {
        System.out.println("Assembling a car.");
    }
}

class Bike implements Vehicle {
    @Override
    public void produce() {
        System.out.println("Producing a bike.");
    }

    @Override
    public void assemble() {
        System.out.println("Assembling a bike.");
    }
}

abstract class VehicleProducer {
    protected Vehicle vehicle;

    public VehicleProducer(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public abstract void produceAndAssemble();
}

class CarProducer extends VehicleProducer {
    public CarProducer(Vehicle vehicle) {
        super(vehicle);
    }

    @Override
    public void produceAndAssemble() {
        System.out.println("Car production process started.");
        vehicle.produce();
        vehicle.assemble();
        System.out.println("Car production process completed.");
    }
}

class BikeProducer extends VehicleProducer {
    public BikeProducer(Vehicle vehicle) {
        super(vehicle);
    }

    @Override
    public void produceAndAssemble() {
        System.out.println("Bike production process started.");
        vehicle.produce();
        vehicle.assemble();
        System.out.println("Bike production process completed.");
    }
}

public class Bridge {
    public static void main(String[] args) {
        VehicleProducer carProducer = new CarProducer(new Car());
        carProducer.produceAndAssemble();

        VehicleProducer bikeProducer = new BikeProducer(new Bike());
        bikeProducer.produceAndAssemble();
    }
}