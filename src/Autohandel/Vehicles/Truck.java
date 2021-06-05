package Autohandel.Vehicles;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

public class Truck extends Vehicle {
    Integer cargoSpace;

    public Truck(String brand, Integer mileage, String color, EnumClass.Segment segment) {
        super(brand, mileage, color, segment);
        this.cargoSpace = ThreadLocalRandom.current().nextInt(100, 300 + 1) * 10;
        this.value = generateValue();
    }

    public BigDecimal generateValue() {
        BigDecimal rand = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(5000.0, 50000.0 + 1));
        return rand.setScale(2, RoundingMode.DOWN);
    }

    public Integer getCargoSpace() {
        return cargoSpace;
    }

    @Override
    public String toString() {
        return " {" + "\n" +
                "Typ = " + getType() + "\n" +
                "Wartość = " + value + "\n" +
                "Marka = " + brand + "\n" +
                "Przebieg = " + mileage + "\n" +
                "Kolor = " + color + "\n" +
                "Segment = " + segment + "\n" +
                "Części = " + efficientElements + "\n" +
                "Pojemność = " + cargoSpace + "\n" + " }";
    }
}
