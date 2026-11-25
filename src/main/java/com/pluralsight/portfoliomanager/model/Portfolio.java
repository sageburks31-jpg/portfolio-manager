package com.pluralsight.portfoliomanager.model;

import java.math.BigDecimal;
import java.util.*;

public class Portfolio implements Valuable{
    String name;
    String owner;
    List<Valuable> valuableList = new ArrayList<>();

    public Portfolio() {
        setName("unnamed");
        setOwner("unknown");
        addToList(new Gold("Gold",new BigDecimal("0.0"),0.0));
        removeFromList(0);
    }
    public Portfolio(String name, String owner, List<Valuable> valuableList) {
        setName(name);
        setOwner(owner);
        setValuableList(valuableList);
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getOwner() {return owner;}
    public void setOwner(String owner) {this.owner = owner;}

    public List<Valuable> getValuableList() {return valuableList;}
    public void setValuableList(List<Valuable> valuableList) {this.valuableList = valuableList;}
    public void addToList(Valuable valuable){valuableList.add(valuable);}
    public void removeFromList(int index){valuableList.remove(index);}

    public BigDecimal getStringTotalValue() {
        BigDecimal value = new BigDecimal("0.0");
        for (Valuable valuable: valuableList) {value = value.add(new BigDecimal(valuable.getValue()));}
        return value;
    }
    @Override
    public String getValue() {
        StringBuilder names = new StringBuilder();
        for (int i = 0; i <= getValuableList().size()-1; i++) {

            String name = getName(getValuableList().get(i));
            names.append(name);
            if (i != getValuableList().size()-1) {names.append(", ");}
        }
        return names.toString();
    }
    public String getStringOfValuable(Valuable valuable) {
        StringBuilder names = new StringBuilder("most: [");

        String name = getName(valuable);
        names.append(name).append(": ").append(valuable.getValue());
        names.append("]");

        return names.toString();
    }

    private String getName(Valuable valuable) {
        return switch (valuable.getClass().getSuperclass().getSimpleName()){
            case "FixedAsset" -> {
                FixedAsset fixedAsset1 = (FixedAsset) valuable;
                yield fixedAsset1.getName();
            }
            case "Account" -> {
                Account fixedAsset1 = (Account) valuable;
                yield fixedAsset1.getName();
            }
            default -> valuable.getClass().getSuperclass().getSimpleName();
        };
    }

    public Valuable getMostValuable() {
        double amount = 0;
        Valuable mostValuableAsset = getValuableList().get(0);

        for (Valuable valuable: getValuableList()) {
            if (amount < Double.parseDouble(String.valueOf(valuable.getValue()))) {
                amount = Double.parseDouble(String.valueOf(valuable.getValue()));
                mostValuableAsset = valuable;
            }
        }
        assert mostValuableAsset != null;
        return mostValuableAsset;
    }
    public Valuable getLeastValuable() {
        double amount = Double.parseDouble(String.valueOf(getValuableList().get(0).getValue()));
        Valuable leastValuableAsset = getValuableList().get(0);

        for (Valuable valuable: getValuableList()) {
            if (amount > Double.parseDouble(String.valueOf(valuable.getValue()))) {
                amount = Double.parseDouble(String.valueOf(valuable.getValue()));
                leastValuableAsset=valuable;
            }
        }
        assert leastValuableAsset != null;
        return leastValuableAsset;
    }

    public String toRow(){
        return String.format("Name: %s | Owner: %s | List of Valuables: %s | Total Value: %s",
                getName(),
                getOwner(),
                getValue(),
                getStringTotalValue());
    }
}
