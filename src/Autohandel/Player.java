package Autohandel;

import Autohandel.Vehicles.Vehicle;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Player {
    final static BigDecimal DEFAULT_STARTING_CASH = new BigDecimal("50000.0");
    final BigDecimal DEFAULT_WASHING_PRICE = new BigDecimal("30");
    String nickname;
    BigDecimal cash;
    ArrayList<Vehicle> vehicles = new ArrayList<>();
    ArrayList<String> transactionHistory = new ArrayList<>();
    ArrayList<Instalment> instalments = new ArrayList<>();

    public Player(String nick) {
        this.nickname = nick;
        this.cash = DEFAULT_STARTING_CASH;
    }

    public void pay(BigDecimal cash) {
        this.cash = this.cash.subtract(cash);
    }
    public void earn(BigDecimal cash) {
        this.cash = this.cash.add(cash);
    }

    public Boolean buyVehicle(Vehicle veh, ArrayList<Vehicle> vehicles) {
        BigDecimal price = new BigDecimal("0");
        price = price.add(DEFAULT_WASHING_PRICE);
        price = price.add(veh.getValue().multiply(new BigDecimal("0.02"), new MathContext(2))); //podatek 2%
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
            transactionHistory.add("Kupiłeś " + veh + " za "+ price);
            return true;
        }
    }

    public Boolean getInterestedClients(Vehicle veh, ArrayList<Client> clients) {
        ArrayList<Client> interestedClients = new ArrayList<>();
        for (Client client: clients) {
            if(client.checkIfInterested(veh))
                interestedClients.add(client);
        }
        System.out.println("Lista klientów zainteresowana tym pojazdem: ");
        int i=0;
        if(interestedClients.isEmpty()) {
            System.out.println("Nikt nie jest zainteresowany tym pojazdem :/");
            return false;
        }
        for (Client client: interestedClients) {
            i++;
            System.out.println("Klient nr " + i + client);
        }
        int choice;
        try {
            Scanner userInput = new Scanner(System.in);
            System.out.println("Podaj numer klienta któemu chcesz sprzedać samochód");
            choice = userInput.nextInt() - 1;
            try {
                return sellVehicle(veh,interestedClients.get(choice),clients);
            }
            catch(IndexOutOfBoundsException ex) {
                System.out.println("Klient z takim numerem nie istnieje");
            }
        }
        catch(InputMismatchException | NumberFormatException ex) {
            System.out.println("Niedozwolony wybór");
        }
        return false;
    }

    public Boolean sellVehicle(Vehicle veh, Client client, ArrayList<Client> clients) {
        BigDecimal tax = veh.getValue().multiply(new BigDecimal("0.02")).setScale(2, RoundingMode.DOWN);
        if(client.getCash().compareTo(veh.getValue()) > 0) {
            transactionHistory.add("Sprzedałeś " + veh + " za "+ veh.getValue());
            this.earn(veh.getValue());
        }
        else {
            System.out.println("Klient nie ma tyle pieniędzy, ALE kupi pojazd na raty");
            instalments.add(new Instalment(veh.getValue().multiply(new BigDecimal("0.10")).setScale(2, RoundingMode.DOWN)));
            transactionHistory.add("Sprzedałeś " + veh + " za "+ veh.getValue() + " w 10 ratach");
        }
        this.pay(tax);
        System.out.println("Zapłaciłeś " + tax + " podatku");
        vehicles.remove(veh);
        clients.remove(client);
        clients.add(Client.AddRandomClient());
        clients.add(Client.AddRandomClient());
        return true;
    }

    public BigDecimal getCash() {
        return this.cash;
    }
    public ArrayList<Vehicle> getVehicles() {
        return this.vehicles;
    }
    public Vehicle getVehicle(int index) {
        return this.vehicles.get(index);
    }
    public ArrayList<String> getTransactionHistory() {
        return this.transactionHistory;
    }

    @Override
    public String toString() {
        return "Gracz: " + nickname;
    }
}
