package Autohandel.Vehicles;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Vehicle {
    Double value;
    String brand;
    Integer mileage;
    String color;
    EnumClass.Segment segment;
    public EfficientElements efficientElements;

    public final static String[] DEFAULT_BRANDS =  {"Fiat", "Volvo", "Citroen", "Skoda", "Renault", "Audi", "Ford"};
    final static String[] DEFAULT_COLORS = {"Blue", "Red", "Black", "White", "Orange", "Green", "Yellow", "Brown", "Silver", "Gray"};

    public Vehicle(String brand, Integer mileage, String color, EnumClass.Segment segment) {
        this.brand = brand;
        this.mileage = mileage;
        this.color = color;
        this.segment = segment;
        this.efficientElements = new EfficientElements(this);
        this.value = generateValue();
    }

    public static ArrayList<Vehicle> generateRandomVehicles(int x) {
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        for(int i = 0; i < x; i++) {
            int randomBrand = ThreadLocalRandom.current().nextInt(0, DEFAULT_BRANDS.length );
            String brand = DEFAULT_BRANDS[randomBrand];
            int mileage = ThreadLocalRandom.current().nextInt(0, 1000000 + 1);  // + 1 to włącznie z tą liczbą
            int randomColor = ThreadLocalRandom.current().nextInt(0, DEFAULT_COLORS.length );
            String color = DEFAULT_COLORS[randomColor];
            int randomSegment = ThreadLocalRandom.current().nextInt(0, EnumClass.Segment.values().length);
            EnumClass.Segment segment = EnumClass.Segment.values()[randomSegment];
            int randomVehicle = ThreadLocalRandom.current().nextInt(0, EnumClass.Vehicle.values().length);
            switch (randomVehicle) {
                case 0 -> vehicles.add(new Car(brand, mileage, color, segment));
                case 1 -> vehicles.add(new Truck(brand, mileage, color, segment));
                case 2 -> vehicles.add(new Motorcycle(brand, mileage, color, segment));
            }
        }

        return vehicles;
    }

    public abstract Double generateValue();

    public EnumClass.Condition getCondition() {
        int efficientElementCounter = efficientElements.getEfficientNumberParts();
        if (efficientElementCounter == 5)
            return EnumClass.Condition.AS_NEW;
        else if (efficientElements.getSuspension())
            return EnumClass.Condition.SUSPENSION_BROKEN;
        return EnumClass.Condition.WRECKAGE;
    }

    @Override
    public String toString() {
        return "Vehicle{" + "\n" +
                "Type=" + this.getClass() + "\n" +
                "value=" + value + "\n" +
                "brand='" + brand + '\'' + "\n" +
                "mileage=" + mileage + "\n" +
                "color='" + color + '\'' + "\n" +
                "segment=" + segment + "\n" +
                "Elements=" + efficientElements + "\n" +
                '}';
    }
}
