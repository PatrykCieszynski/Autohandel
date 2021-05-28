package Autohandel.Vehicles;

import java.util.HashMap;
import java.util.Map;

public abstract class Vehicle {
    final Double value;
    final String brand;
    final Integer mileage;
    final String color;
    EnumClass.Segment segment;
    Map<String, Boolean> efficientElements = new HashMap<>();

    public Vehicle(Double value, String brand, Integer mileage, String color) {
        this.value = value;
        this.brand = brand;
        this.mileage = mileage;
        this.color = color;
        //testy
        this.segment = EnumClass.Segment.premium;
        efficientElements.put("Brakes",true);
        efficientElements.put("Suspension",true);
        efficientElements.put("Engine",false);
        efficientElements.put("Body",true);
        efficientElements.put("Gearbox",true);
    }

    public EnumClass.Condition getCondition() {
        int efficientElementCounter = 0;
        for (Boolean value: efficientElements.values()) {
            if(value)
                efficientElementCounter++;
        }
        if (efficientElementCounter == 5)
            return EnumClass.Condition.As_New;
        else if (efficientElements.get("Suspension"))
            return EnumClass.Condition.Suspension_Broken;
        return EnumClass.Condition.Wreckage;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "value=" + value +
                ", brand='" + brand + '\'' +
                ", mileage=" + mileage +
                ", color='" + color + '\'' +
                ", segment=" + segment +
                ", Elements=" + efficientElements +
                '}';
    }
}
