package Autohandel.Vehicles;

public class Truck extends Vehicle{
    Integer cargoSpace;

    public Truck(String brand, Integer mileage, String color, EnumClass.Segment segment) {
        super(brand, mileage, color, segment);
        this.cargoSpace = cargoSpace;
        this.value = generateValue();
    }
    public Double generateValue() {
        return 10.0;
    }
}
