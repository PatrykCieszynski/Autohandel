package Autohandel.Vehicles;

public class Car extends Vehicle{

    public Car(String brand, Integer mileage, String color, EnumClass.Segment segment) {
        super(brand, mileage, color, segment);
        this.value = generateValue();
    }
    public Double generateValue() {
        return 15.0;
    }
}
