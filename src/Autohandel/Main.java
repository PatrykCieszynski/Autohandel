package Autohandel;

import Autohandel.Vehicles.Vehicle;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Player Cieszu = new Player("Cieszu");
        ArrayList<Vehicle> Vehicles = Vehicle.generateRandomVehicles(10);
        ArrayList<Client> Clients = Client.generateRandomClients(50);
        System.out.println(Vehicles.get(0));
        System.out.println(Vehicles.get(0).efficientElements.repair(Cieszu));
        System.out.println(Vehicles.get(0));
    }
}
