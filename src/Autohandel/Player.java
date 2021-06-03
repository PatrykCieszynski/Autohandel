package Autohandel;

import Autohandel.Vehicles.Vehicle;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

public class Player {
    final static BigDecimal DEFAULT_STARTING_CASH = new BigDecimal("50000.0");
    String nickname;
    BigDecimal cash;
    ArrayList<Vehicle> vehicles = new ArrayList<>();

    public Player(String nick) {
        this.nickname = nick;
        this.cash = DEFAULT_STARTING_CASH;
    }

    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }
    public Vehicle getVehicle(int index) {
        return vehicles.get(index);
    }

    public void pay(BigDecimal cash) {
        this.cash = this.cash.subtract(cash);
    }

    public Boolean buyVehicle(Vehicle veh, ArrayList<Vehicle> vehicles) {
        BigDecimal price = BigDecimal.valueOf(0);
        price = price.add(BigDecimal.valueOf(30)); //mycie za 30zł
        price = price.add(veh.getValue().multiply(BigDecimal.valueOf(0.02), new MathContext(2))); //podatek 2%
        price = price.add(veh.getValue());
        if(this.cash.compareTo(price) < 0) { // compareTo zwraca 1(większe), -1(mniejsze), 0(równe)
            System.out.println("Nie stać cię! Cena z podatkiem i myciem " + price);
            return false;
        }
        else {
            this.cash = this.cash.subtract(price);
            this.vehicles.add(veh);
            vehicles.remove(veh);
            System.out.println("Udało się kupić pojazd za " + price);
            return true;
        }
    }

    public BigDecimal getCash() {
        return this.cash;
    }

    @Override
    public String toString() {
        return "Gracz: " + nickname + "\n";
    }
}
