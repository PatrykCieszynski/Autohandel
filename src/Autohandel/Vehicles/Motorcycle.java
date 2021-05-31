package Autohandel.Vehicles;

public class Motorcycle extends Vehicle {

    public Motorcycle(String brand, Integer mileage, String color, EnumClass.Segment segment) {
        super(brand, mileage, color, segment);
        this.value = generateValue();
    }
    public Double generateValue() {
        return 5.0;
    }
}
