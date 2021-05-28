package Autohandel.Vehicles;

public class Truck extends Vehicle{
    final Integer cargoSpace;

    public Truck(Double value, String brand, Integer mileage, String color, Integer cargoSpace) {
        super(value, brand, mileage, color);
        this.cargoSpace = cargoSpace;
    }
}
