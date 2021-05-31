package Autohandel;

import Autohandel.Vehicles.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;


public class Client {
    String name;
    String surname;
    Double cash;
    String[] favBrands;
    EnumClass.Vehicle interestedInVehicle;
    EnumClass.Condition interestedInCondition;

    final static String[] DEFAULT_NAMES =  {"Jan", "Maciej", "Michał", "Aleks", "Mikołaj", "Kacper", "Mirosław", "Amadeusz", "Piotr", "Paweł", "Dawid", "Olaf", "Florian", "Józef", "Krzysztof"};
    final static String[] DEFAULT_SURNAMES = {"Włodarczyk", "Lewandowski", "Kalinowski", "Głowacki", "Witkowski", "Kubiak", "Sokołowski", "Pawlak", "Borkowski", "Baranowski", "Pietrzak", "Jaworski", "Mazurek" , "Wysocki" , "Mróz"};

    public Client(String name, String surname, Double cash,String[] favBrands, EnumClass.Vehicle interestedInVehicle, EnumClass.Condition interestedInCondition) {
        this.name = name;
        this.surname = surname;
        this.cash = cash;
        this.favBrands = favBrands;
        this.interestedInVehicle = interestedInVehicle;
        this.interestedInCondition = interestedInCondition;
    }

    public static ArrayList<Client> generateRandomClients(int x) {
        ArrayList<Client> clients = new ArrayList<>();
        for(int i = 0; i < x; i++) {
            clients.add(AddRandomClient());
        }
        return clients;
    }

    public static Client AddRandomClient() {
        int randomName = ThreadLocalRandom.current().nextInt(0, DEFAULT_NAMES.length);
        String name = DEFAULT_NAMES[randomName];
        int randomSurname = ThreadLocalRandom.current().nextInt(0, DEFAULT_SURNAMES.length);
        String surname = DEFAULT_NAMES[randomSurname];
        Double cash = (double) ThreadLocalRandom.current().nextInt(15000, 45000 + 1);
        int randomBrand1;
        int randomBrand2;
        do {
            randomBrand1 = ThreadLocalRandom.current().nextInt(0, Vehicle.DEFAULT_BRANDS.length);
            randomBrand2 = ThreadLocalRandom.current().nextInt(0, Vehicle.DEFAULT_BRANDS.length);
        } while(randomBrand1 == randomBrand2);
        String[] favBrands = new String[]{Vehicle.DEFAULT_BRANDS[randomBrand1], Vehicle.DEFAULT_BRANDS[randomBrand2]};
        int randomVehicle = ThreadLocalRandom.current().nextInt(0, EnumClass.Vehicle.values().length);
        EnumClass.Vehicle vehicle = EnumClass.Vehicle.values()[randomVehicle];
        int randomCondition = ThreadLocalRandom.current().nextInt(0, 100);
        EnumClass.Condition condition;
        if(randomCondition < 10)
            condition = EnumClass.Condition.WRECKAGE;
        else if(randomCondition < 25)
            condition = EnumClass.Condition.SUSPENSION_BROKEN;
        else
            condition = EnumClass.Condition.AS_NEW;
        return new Client(name, surname, cash, favBrands, vehicle, condition);
    }

    public boolean checkVehicleType(Vehicle veh) {
        if(interestedInVehicle == EnumClass.Vehicle.CAR && veh instanceof Car)
            return true;
        else if (interestedInVehicle == EnumClass.Vehicle.TRUCK && veh instanceof Truck)
            return true;
        return interestedInVehicle == EnumClass.Vehicle.MOTORCYCLE && veh instanceof Motorcycle;
    }
    public boolean checkVehicleCondition(Vehicle veh) {
        if(interestedInCondition == EnumClass.Condition.WRECKAGE)
            return true;
        else if (interestedInCondition == EnumClass.Condition.SUSPENSION_BROKEN && veh.getCondition() != EnumClass.Condition.WRECKAGE)
            return true;
        else return interestedInCondition == EnumClass.Condition.AS_NEW && veh.getCondition() == EnumClass.Condition.AS_NEW;
    }

    @Override
    public String toString() {
        return "Client{" + "\n" +
                "cash=" + cash + "\n" +
                "favBrand=" + Arrays.toString(favBrands) + "\n" +
                "interestedInVehicle=" + interestedInVehicle + "\n" +
                "interestedInCondition=" + interestedInCondition + "\n" +
                "}";
    }
}
