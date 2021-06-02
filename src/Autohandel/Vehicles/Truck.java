package Autohandel.Vehicles;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

public class Truck extends Vehicle{
    Integer cargoSpace;

    public Truck(String brand, Integer mileage, String color, EnumClass.Segment segment) {
        super(brand, mileage, color, segment);
        this.cargoSpace = cargoSpace;
        this.value = generateValue();
    }
    public BigDecimal generateValue() {
        BigDecimal rand = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(5000.0, 50000.0 + 1));
        return rand.setScale(2, RoundingMode.DOWN);
    }
}
