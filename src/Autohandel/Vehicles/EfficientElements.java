package Autohandel.Vehicles;

import Autohandel.Player;

import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;
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
        if(this.elements.get(choosePart)) {
            System.out.println("Ta część jest sprawna");
            return false;
        }
        int mechanic;
        System.out.println("Wybierz mechanika:");
        System.out.println("1. Janusz - " + "" + "100% na udaną naprawę");
        System.out.println("2. Marian - " + "" + "90% na udaną naprawę");
        System.out.println("3. Adrian - " + "" + "80% na udaną naprawę , ALE 2% ŻE ZEPSUJE COŚ INNEGO");
        mechanic = userInput.nextInt();
        switch(mechanic) {
            case 1:
                //pobranie pieniędzy
                this.elements.put(choosePart,true);
                return true;
                case 2:
                    //pobranie pieniędzy
                    if(randomInt >= 10) {
                        this.elements.put(choosePart,true);
                        return true;
                    }
                    else {
                        System.out.println("Mechanik nie podołał :/");
                        return false;
                    }
                case 3:
                    //pobranie pieniędzy
                    if(randomInt >= 20) {
                        this.elements.put(choosePart,true);
                        return true;
                    }
                    else if(randomInt < 2) {
                        System.out.println("Mechanik nie podołał, a nawet zepsuł inną część :/");
                        this.elements.put(getFirstGoodPart(),false);
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

    public String getFirstGoodPart() {
        if (getEfficientNumberParts() == 0)
            return null;
        for (Map.Entry<String,Boolean> collection: elements.entrySet()) {
            if(collection.getValue())
                return collection.getKey();
        }
        return null;
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
