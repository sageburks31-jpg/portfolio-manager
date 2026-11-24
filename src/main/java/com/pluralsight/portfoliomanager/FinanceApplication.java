package com.pluralsight.portfoliomanager;

import com.pluralsight.portfoliomanager.model.*;

import java.math.BigDecimal;

public class FinanceApplication {

    public static void main(String[] args) {
        House house = new House("Ranch house", new BigDecimal("325000.00"), 1998, 1850, 3);
        Gold gold = new Gold("Gold coins", new BigDecimal("12000.00"), 5.0);
        Jewelry ring = new Jewelry("Grandma's ring", new BigDecimal("2500.00"), 14.0);
        BankAccount account1 = new BankAccount(123, "Pam", new BigDecimal("12500"));
        //Valuable account2 = new BankAccount(456, "Gary", new BigDecimal("1500"));

        account1.deposit(new BigDecimal("100"));
        System.out.println(house.getName() + ": $" + house.getValue());
        System.out.println(gold.getName() + ": $" + gold.getValue());
        System.out.println(ring.getName() + ": $" + ring.getValue()+"\n");

        Portfolio portfolio = new Portfolio();
        portfolio.addToList(gold);
        portfolio.addToList(ring);
        System.out.println(portfolio.toRow());
        System.out.println(portfolio.getMostValuable());
        System.out.println(portfolio.getLeastValuable());
    }
}
