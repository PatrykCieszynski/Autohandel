package Autohandel;

import Autohandel.Vehicles.Car;

import java.util.ArrayList;

public class Player {
    final Double cash;
    ArrayList<Car> Vehicles = new ArrayList<>();

    public Player(Double cash) {
        this.cash = cash;
    }
}
