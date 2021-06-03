package Autohandel;

import Autohandel.Vehicles.Vehicle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    static ArrayList<Player> setupPlayers() {
        ArrayList<Player> players = new ArrayList<>();
        int playersNumber;
        while(true) {
            try {
                Scanner userInput = new Scanner(System.in);
                System.out.println("Podaj liczbę graczy: ");
                playersNumber = userInput.nextInt();
                for(int i = 0; i < playersNumber; i++) {
                    System.out.println("Podaj imię " + (i+1) + " gracza: ");
                    String name = userInput.next();
                    players.add(new Player(name));
                }
                break;
            }
            catch(InputMismatchException | NumberFormatException ex) {
                System.out.println("Niepoprawna liczba");
            }
        }
        return players;
    }
    static Boolean play(Player activePlayer, int turn, ArrayList<Client> clients, ArrayList<Vehicle> vehicles) {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Tura: " + turn);
        System.out.println(activePlayer);
        System.out.println("1.Przeglądaj samochody");
        System.out.println("2.Kup samochód");
        System.out.println("3.Przeglądaj posiadane samochody");
        System.out.println("4.Napraw samochód");
        System.out.println("5.Przejrzyj wszystkich klientów");
        System.out.println("6.Sprzedaj samochód");
        System.out.println("7.Kup reklamę");
        System.out.println("8.Sprawdź stan konta");
        System.out.println("9.Sprawdź historię transakcji");
        System.out.println("10.Sprawdź historię napraw pojazdu");
        System.out.println("11.Sprawdź sumę kosztów napraw i mycia pojazdu");
        int choice = userInput.nextInt();
        return switch(choice) {
            case 1 -> listVehicles(vehicles);
            case 2 -> buyVehicle(activePlayer, vehicles);
            case 3 -> listYourVehicles(activePlayer);
            case 4 -> repairVehicle(activePlayer);
            case 5 -> listClients(clients);
            case 6 -> sellVehicle(activePlayer, clients);
            case 7 -> buyAd(activePlayer, clients);
            case 8 -> checkAccount(activePlayer);
            case 9 -> checkAccountHistory(activePlayer);
            case 10 -> checkRepairHistory(activePlayer);
            case 11 -> checkRepairCostSum(activePlayer);
            default -> {
                System.out.println("Niedozwolony wybór");
                yield false;
            }
        };
    }

    static Boolean listVehicles(ArrayList<Vehicle> vehicles) {
        System.out.println("Samochody dostępne na rynku");
        int i = 1;
        for (Vehicle veh: vehicles) {
            System.out.println("Samochód nr " + i + veh);
            i++;
        }
        return false;
    }
    static Boolean buyVehicle(Player activePlayer, ArrayList<Vehicle> vehicles) {
        listVehicles(vehicles);
        int choice;
        while(true) {
            try {
                Scanner userInput = new Scanner(System.in);
                System.out.println("Podaj numer samochodu który chcesz kupić");
                choice = userInput.nextInt() - 1;
                try {
                    return activePlayer.buyVehicle(vehicles.get(choice),vehicles);
                }
                catch(IndexOutOfBoundsException ex) {
                    System.out.println("Pojazd z tym numerem nie istnieje, spróbuj ponownie");
                }
                break;
            }
            catch(InputMismatchException | NumberFormatException ex) {
                System.out.println("Niedozwolony wybór, spróbuj ponownie");
            }
        }
        return false;
    }
    static Boolean listYourVehicles(Player activePlayer) {
        System.out.println("Twoje samochody");
        int i = 1;
        for (Vehicle veh: activePlayer.getVehicles()) {
            System.out.println("Samochód nr " + i + veh);
            i++;
        }
        return false;
    }
    static Boolean repairVehicle(Player activePlayer) {
        listYourVehicles(activePlayer);
        int choice;
        while(true) {
            try {
                Scanner userInput = new Scanner(System.in);
                System.out.println("Podaj numer samochodu który chcesz naprawić");
                choice = userInput.nextInt() - 1;
                try {
                    return activePlayer.getVehicle(choice).getEfficientElements().repair(activePlayer);
                }
                catch(IndexOutOfBoundsException ex) {
                    System.out.println("Pojazd z tym numerem nie istnieje, spróbuj ponownie");
                }
                break;
            }
            catch(InputMismatchException | NumberFormatException ex) {
                System.out.println("Nie posiadasz samochodu z takim numerem");
            }
        }
        return false;
    }
    static Boolean listClients(ArrayList<Client> clients) {
        System.out.println("Dostępni klienci");
        int i = 1;
        for (Client client: clients) {
            System.out.println("Klient nr " + i + client);
            i++;
        }
        return false;
    }
    static Boolean sellVehicle(Player activePlayer, ArrayList<Client> clients) {
        // TODO
        return true;
    }
    static Boolean buyAd(Player activePlayer, ArrayList<Client> clients) {
        final BigDecimal DEFAULT_NEWSPAPER_AD_COST = new BigDecimal("200");
        final BigDecimal DEFAULT_INTERNET_AD_COST = new BigDecimal("40");

        int choice;
        while(true) {
            try {
                Scanner userInput = new Scanner(System.in);
                System.out.println("Jaką reklamę chcesz kupić?");
                System.out.println("1.Ogłoszenie w gazecie - koszt " + DEFAULT_NEWSPAPER_AD_COST);
                System.out.println("2.Ogłoszenie w internecie - ksozt " + DEFAULT_INTERNET_AD_COST);
                choice = userInput.nextInt();
                switch(choice) {
                    case 1 -> {
                        activePlayer.pay(DEFAULT_NEWSPAPER_AD_COST);
                        int r = ThreadLocalRandom.current().nextInt(4, 6 + 1);
                        for(int i = 0; i < r; i++) {
                            clients.add(Client.AddRandomClient());
                        }
                    }
                    case 2 -> {
                        activePlayer.pay(DEFAULT_INTERNET_AD_COST);
                        clients.add(Client.AddRandomClient());
                    }
                    default -> {
                        System.out.println("Niepoprawny wybór");
                        return false;
                    }
                }
                break;
            }
            catch(InputMismatchException | NumberFormatException ex) {
                System.out.println("Niepoprawny wybór");
            }
        }
        return false;
    }
    static Boolean checkAccount(Player activePlayer) {
        System.out.println("Stan konta: " + activePlayer.getCash());
        return false;
    }
    static Boolean checkAccountHistory(Player activePlayer) {
        //TODO Jeszcze nie wiem jak :/
        return false;
    }
    static Boolean checkRepairHistory(Player activePlayer) {
        //TODO Jeszcze nie wiem jak :/
        return false;
    }
    static Boolean checkRepairCostSum(Player activePlayer) {
        //TODO Jeszcze nie wiem jak :/
        return false;
    }

    public static void main(String[] args) {
        int turn = 0;
        ArrayList<Player> players = setupPlayers();
        ArrayList<Client> clients = Client.generateRandomClients(10);
        do { //Pętla dla gry
            turn++;
            ArrayList<Vehicle> vehicles = Vehicle.generateRandomVehicles(15);
            for (Player activePlayer: players) { // pętla dla tury gracza
                Boolean condition;
                do {
                    condition = play(activePlayer, turn, clients, vehicles);
                } while(!condition);
            }
        } while(true);

    }
}
