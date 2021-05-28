package Autohandel;

import Autohandel.Vehicles.*;

import java.util.Arrays;


public class Client {
    static final Integer DEFAULT_FAVOURITE_BRANDS_NUMBER = 2;
    final Double cash;
    final String[] favBrand;
    EnumClass.Vehicle interestedInVehicle;
    EnumClass.Condition interestedInCondition;

    public Client(Double cash, EnumClass.Vehicle interestedInVehicle, EnumClass.Condition interestedInCondition) {
        this.cash = cash;
        this.favBrand = new String[DEFAULT_FAVOURITE_BRANDS_NUMBER];
        this.interestedInVehicle = interestedInVehicle;
        this.interestedInCondition = interestedInCondition;
    }
    public boolean checkVehicleType(Vehicle veh) {
        if(interestedInVehicle == EnumClass.Vehicle.Car && veh instanceof Car)
            return true;
        else if (interestedInVehicle == EnumClass.Vehicle.Truck && veh instanceof Truck)
            return true;
        return interestedInVehicle == EnumClass.Vehicle.Motorcycle && veh instanceof Motorcycle;
    }
    public boolean checkVehicleCondition(Vehicle veh) {
        if(interestedInCondition == EnumClass.Condition.Wreckage)
            return true;
        else if (interestedInCondition == EnumClass.Condition.Suspension_Broken && veh.getCondition() != EnumClass.Condition.Wreckage)
            return true;
        else return interestedInCondition == EnumClass.Condition.As_New && veh.getCondition() == EnumClass.Condition.As_New;
    }

    @Override
    public String toString() {
        return "Client{" +
                "cash=" + cash +
                ", favBrand=" + Arrays.toString(favBrand) +
                ", interestedInVehicle=" + interestedInVehicle +
                ", interestedInCondition=" + interestedInCondition +
                '}';
    }
}
