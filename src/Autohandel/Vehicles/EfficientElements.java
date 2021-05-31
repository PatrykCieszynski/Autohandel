package Autohandel.Vehicles;

import java.util.Random;

public class EfficientElements {
    Boolean brakes;
    Boolean suspension;
    Boolean engine;
    Boolean body;
    Boolean gearbox;
    Random random = new Random();

    public EfficientElements() {
        this.brakes = random.nextBoolean();
        this.suspension = random.nextBoolean();
        this.engine = random.nextBoolean();
        this.body = random.nextBoolean();
        this.gearbox = random.nextBoolean();
    }

    public int getEfficientNumberParts() {
        int efficientparts = 0;
        if (this.brakes)
            efficientparts++;
        if (this.suspension)
            efficientparts++;
        if (this.engine)
            efficientparts++;
        if (this.body)
            efficientparts++;
        if (this.gearbox)
            efficientparts++;
        return efficientparts;
    }
    public Boolean getSuspension() {
        return this.suspension;
    }

    @Override
    public String toString() {
        return "EfficientElements{" + "\n" +
                "   brakes=" + brakes + "\n" +
                "   suspension=" + suspension + "\n" +
                "   engine=" + engine + "\n" +
                "   body=" + body + "\n" +
                "   gearbox=" + gearbox + "\n" +
                "   }";
    }
}
