package Autohandel;

import Autohandel.Vehicles.EnumClass;
import Autohandel.Vehicles.Truck;

public class Main {

    public static void main(String[] args) {
        Truck a = new Truck(1500.0,"Mercedes",15000,"blue",2000);
        Client Jan = new Client(120.0, EnumClass.Vehicle.Truck, EnumClass.Condition.Wreckage);
        System.out.println(a);
        System.out.println(Jan);
        System.out.println(Jan.checkVehicleType(a));
        System.out.println(Jan.checkVehicleCondition(a));
    }
}
