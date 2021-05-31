package Autohandel;

import Autohandel.Vehicles.Vehicle;

import java.util.ArrayList;

public class Player {
    final static Double DEFAULT_STARTING_CASH = 30000.0;
    String nickname;
    Double cash;
    ArrayList<Vehicle> vehicles = new ArrayList<>();

    public Player(String nick) {
        this.nickname = nick;
        this.cash = DEFAULT_STARTING_CASH;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }

    @Override
    public String toString() {
        return "Player{" +
                "nickname='" + nickname + '\'' +
                ", cash=" + cash +
                '}';
    }
}
