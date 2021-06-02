package Autohandel.Vehicles;

import Autohandel.Player;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class EfficientElements {
    TreeMap<String,Boolean> elements;
    Random random = new Random();
    Vehicle vehicle;

    public EfficientElements(Vehicle vehicle) {
        this.elements = new TreeMap<>();
        elements.put("Brakes",random.nextBoolean());
        elements.put("Suspension",random.nextBoolean());
        elements.put("Engine",random.nextBoolean());
        elements.put("Body",random.nextBoolean());
        elements.put("Gearbox",random.nextBoolean());
        this.vehicle = vehicle;
    }

    public Boolean repair(Player owner) {
        Scanner userInput = new Scanner(System.in);
        int randomInt = ThreadLocalRandom.current().nextInt(0, 100);
        System.out.println("Wybierz część:");
        String choosePart = getPart();
        BigDecimal multiplier = getMultiplier(choosePart);
        BigDecimal repairCost = getRepairCost(choosePart, vehicle.getBrand());
        if(this.elements.get(choosePart)) {
            System.out.println("Ta część jest sprawna");
            return false;
        }
        int mechanic;
        System.out.println("Wybierz mechanika:");
        System.out.println("1. Janusz - " + "" + "100% na udaną naprawę, koszt " + repairCost.multiply(new BigDecimal("1.5")));
        System.out.println("2. Marian - " + "" + "90% na udaną naprawę, koszt " + repairCost.multiply(new BigDecimal("1.2")));
        System.out.println("3. Adrian - " + "" + "80% na udaną naprawę , ALE 2% ŻE ZEPSUJE COŚ INNEGO, koszt " + repairCost);
        mechanic = userInput.nextInt();
        switch(mechanic) {
            case 1:
                owner.pay(repairCost.multiply(new BigDecimal("1.5")));
                this.elements.put(choosePart,true);
                vehicle.setValue(multiplier);
                return true;
            case 2:
                owner.pay(repairCost.multiply(new BigDecimal("1.2")));
                if(randomInt >= 10) {
                    this.elements.put(choosePart,true);
                    vehicle.setValue(multiplier);
                    return true;
                }
                else {
                    System.out.println("Mechanik nie podołał :/");
                    return false;
                }
            case 3:
                owner.pay(repairCost);
                if(randomInt >= 20) {
                    this.elements.put(choosePart,true);
                    vehicle.setValue(multiplier);
                    return true;
                }
                else if(randomInt < 2) {
                    String part = getRandomGoodPart();
                    if(part != null) {
                        System.out.println("Mechanik nie podołał, a nawet zepsuł inną część :/");
                        this.elements.put(getRandomGoodPart(),false);
                    }
                    else
                    {
                        System.out.println("Mechanik nie podołał, gdyby mógł to jeszcze by coś zepsuł, ale to totalny wrak");
                    }

                    return false;
                }
                else {
                    System.out.println("Mechanik nie podołał :/");
                    return false;
                }
            default:
                return false;
        }
    }
    public BigDecimal getRepairCost(String part, String brand) {
        BigDecimal basePrice = getRepairCostPart(part);
        return basePrice.multiply( switch(brand) {
            case "Fiat" -> new BigDecimal("1.2");
            case "Volvo" -> new BigDecimal("1.3");
            case "Citroen" -> new BigDecimal("1.1");
            case "Skoda" -> new BigDecimal("1.15");
            case "Renault" -> new BigDecimal("1.25");
            case "Audi" -> new BigDecimal("1.13");
            case "Ford" -> new BigDecimal("1.28");
            default -> new BigDecimal("0");
        });

    }
    public BigDecimal getRepairCostPart(String part) {
        return switch(part) {
            case "Brakes" -> new BigDecimal("60");
            case "Suspension" -> new BigDecimal("500");
            case "Engine" -> new BigDecimal("4000");
            case "Body" -> new BigDecimal("250");
            case "Gearbox" -> new BigDecimal("1800");
            default -> new BigDecimal("0");
        };
    }

    public String getPart() {
        Scanner userInput = new Scanner(System.in);
        int choosePart;
        System.out.println("1. " + "Hamulce " + (elements.get("Brakes") ? "sprawne" : "niesprawne"));
        System.out.println("2. " + "Zawieszenie " + (elements.get("Suspension") ? "sprawne" : "niesprawne"));
        System.out.println("3. " + "Silnik " + (elements.get("Engine") ? "sprawne" : "niesprawne"));
        System.out.println("4. " + "Karoseria " + (elements.get("Body") ? "sprawne" : "niesprawne"));
        System.out.println("5. " + "Skrzynia biegów " + (elements.get("Gearbox") ? "sprawne" : "niesprawne"));
        choosePart = (userInput.nextInt());
        return switch (choosePart) {
            case 1 -> "Brakes";
            case 2 -> "Suspension";
            case 3 -> "Engine";
            case 4 -> "Body";
            case 5 -> "Gearbox";
            default -> null;
        };
    }

    public BigDecimal getMultiplier(String part) {
        return switch (part) {
            case "Brakes" -> new BigDecimal("1.1");
            case "Suspension" -> new BigDecimal("1.2");
            case "Engine" -> new BigDecimal("2");
            case "Body", "Gearbox" -> new BigDecimal("1.5");
            default -> new BigDecimal("1");
        };
    }

    public int getEfficientNumberParts() {
        int counter = 0;
        for (Map.Entry<String,Boolean> collection: elements.entrySet()) {
            if(collection.getValue())
                counter++;
        }
        return counter;
    }
    public Boolean getSuspension() {
        return this.elements.get("Suspension");
    }

    public String getRandomGoodPart() {
        if(getEfficientNumberParts() == 0)
            return null;
        else{
            ArrayList<String> goodparts = new ArrayList<>();
            for (Map.Entry<String,Boolean> collection: elements.entrySet()) {
                if(collection.getValue())
                    goodparts.add(collection.getKey());
            }
            return goodparts.get(ThreadLocalRandom.current().nextInt(0, goodparts.size()));
        }
    }

    @Override
    public String toString() {
        return "EfficientElements{" + "\n" +
                "   brakes=" + elements.get("Brakes") + "\n" +
                "   suspension=" + elements.get("Suspension") + "\n" +
                "   engine=" + elements.get("Engine") + "\n" +
                "   body=" + elements.get("Body") + "\n" +
                "   gearbox=" + elements.get("Gearbox") + "\n" +
                "   }";
    }
}
