package Autohandel.Vehicles;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Vehicle {
    BigDecimal value;
    String brand;
    Integer mileage;
    String color;
    EnumClass.Segment segment;
    EfficientElements efficientElements;
    ArrayList<String> repairHistory = new ArrayList<>();
    BigDecimal sumOfRepairs;

    final static String[] DEFAULT_BRANDS =  {"Fiat", "Volvo", "Citroen", "Skoda", "Renault", "Audi", "Ford"};
    final static String[] DEFAULT_COLORS = {"Blue", "Red", "Black", "White", "Orange", "Green", "Yellow", "Brown", "Silver", "Gray"};

    public Vehicle(String brand, Integer mileage, String color, EnumClass.Segment segment) {
        this.brand = brand;
        this.mileage = mileage;
        this.color = color;
        this.segment = segment;
        this.efficientElements = new EfficientElements(this);
        this.value = generateValue();
        this.sumOfRepairs = new BigDecimal("0");
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

    public abstract BigDecimal generateValue();

    public void setValue(BigDecimal multiplier) {
        this.value = this.value.multiply(multiplier);
    }
    public void addToRepairHistory(String repair) {
        repairHistory.add(repair);
    }
    public void addToSumOfRepairs(BigDecimal repair) {
        sumOfRepairs = sumOfRepairs.add(repair);
    }

    public EnumClass.Condition getCondition() {
        int efficientElementCounter = efficientElements.getEfficientNumberParts();
        if (efficientElementCounter == 5)
            return EnumClass.Condition.AS_NEW;
        else if (efficientElements.getSuspension())
            return EnumClass.Condition.SUSPENSION_BROKEN;
        return EnumClass.Condition.WRECKAGE;
    }
    public static String[] getDefaultBrands() {
        return DEFAULT_BRANDS;
    }
    public String getBrand() {
        return this.brand;
    }
    public BigDecimal getValue() {
        return this.value;
    }
    public EfficientElements getEfficientElements() {
        return this.efficientElements;
    }
    public String getType() {
        if(this instanceof Car) return "Samochód";
        else if(this instanceof Truck) return "Dostawczak";
        else return "Motocykl";
    }
    public ArrayList<String> getRepairHistory() {
        return this.repairHistory;
    }
    public BigDecimal getSumOfRepairs() {
        return this.sumOfRepairs;
    }

    @Override
    public String toString() {
        return  " {" + "\n" +
                "Typ = " + getType() + "\n" +
                "Wartość = " + value + "\n" +
                "Marka = " + brand + "\n" +
                "Przebieg = " + mileage + "\n" +
                "Kolor = " + color + "\n" +
                "Segment = " + segment + "\n" +
                "Części = " + efficientElements + "\n" + " }";
    }
}
