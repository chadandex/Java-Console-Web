package com.sg.vendingmachinespringmvc.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Chad
 */
public class Change {
    private double pennies;
    private double nickels;
    private double dimes;
    private double quarters;
    private double tmp=0;

    public Change(double quarters, double dimes, double nickels, double pennies) {
        this.quarters = quarters;
        this.dimes = dimes;
        this.nickels = nickels;
        this.pennies = pennies;
    }

    public double getPennies(double money) {
        BigDecimal bd = new BigDecimal(Double.toString(money));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        money = bd.doubleValue();
        pennies = money / .01;

        return pennies;
    }

    public void setPennies(double pennies) {
        this.pennies = pennies;
    }

    public double getNickels(double money) {

        tmp = money / .05;
        nickels = (int) tmp;

        return nickels;
    }

    public void setNickels(double nickels) {
        this.nickels = nickels;
    }

    public double getDimes(double money) {

        tmp = money / .10;
        dimes = (int) tmp;

        return dimes;
    }

    public void setDimes(double dimes) {
        this.dimes = dimes;
    }

    public double getQuarters(double money) {

        tmp = money / .25;
        quarters = (int) tmp;
 
        return quarters;
    }

    public void setQuarters(double quarters) {
        this.quarters = quarters;
    }

    public String getChange(double money) {
        String result="";
        
        if (money == 0.00) { // used all money
            result = "";
        }
        if (money >= .25) { // quarters
            result = "Quarters: " + (int) getQuarters(money);
            money = money % .25;
        }
        if (money >= .1) { // dimes
            result = result + " Dimes: " + (int) getDimes(money);
            money = money % .1;
        }
        if (money >= .05) { // nickels
            result = result + " Nickels: " + (int) getNickels(money);
            money = money % .05;
        }
        BigDecimal bd = new BigDecimal(Double.toString(money));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        money = bd.doubleValue(); // BigDecimal so if statement gets a rounded num
        if (money >= .01) { // pennies
            result = result + " Pennies: " + (int) getPennies(money);
        }
        return result;
    }
}
