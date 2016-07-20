package fr.epita.sigl.mepa.front.model.investment;

import org.hibernate.validator.constraints.NotBlank;


/**
 * Created by Valentin ZHENG on 20/07/2016.
 */
public class InvestAmount {
    private int moneyAmount;

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    /*
    public void setMoneyAmount(int moneyAmount) {
            this.moneyAmount = moneyAmount > 0 ? moneyAmount : 0;
    }
    */
}
