package Autohandel;

import java.math.BigDecimal;

public class Instalment {
    BigDecimal amount;
    Integer turnsLeft;

    public Instalment(BigDecimal amount) {
        this.amount = amount;
        this.turnsLeft = 10;
    }
    public BigDecimal cashOutInstalment() {
        this.turnsLeft--;
        return this.amount;
    }
    public Integer getTurnsLeft() {
        return this.turnsLeft;
    }
    public BigDecimal getInstalmentAmount() {
        return this.amount;
    }
}
