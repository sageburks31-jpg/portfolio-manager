package com.pluralsight.portfoliomanager.model;

import java.math.BigDecimal;
import java.util.*;

public class Portfolio {
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

    public BigDecimal getTotalValue() {
        BigDecimal value = new BigDecimal("0.0");
        for (Valuable valuable: valuableList) {value = value.add(valuable.getValue());}
        return value;
    }
    private String getListOfNames() {
        StringBuilder names = new StringBuilder("[");

        for (int i = 0; i <= getValuableList().size()-1; i++) {
            FixedAsset fixedAsset;

            try {fixedAsset = (FixedAsset) getValuableList().get(i);}
            catch (RuntimeException e) {continue;}

            names.append(fixedAsset.getName()).append(": ").append(fixedAsset.getValue());
            if (i != getValuableList().size()-1) {names.append(", ");}
        }
        names.append("]");
        return names.toString();
    }
    public String getMostValuable() {
        StringBuilder names = new StringBuilder("most: [");
        double amount = 0;
        FixedAsset fixedAsset;
        FixedAsset mostValuableAsset = null;

        for (Valuable valuable: getValuableList()) {
            try {fixedAsset = (FixedAsset) valuable;}
            catch (RuntimeException e) {continue;}

            if (amount < Double.parseDouble(String.valueOf(valuable.getValue()))) {
                amount = Double.parseDouble(String.valueOf(valuable.getValue()));
                mostValuableAsset = fixedAsset;
            }
        }
        assert mostValuableAsset != null;
        names.append(mostValuableAsset.getName()).append(": ").append(amount);
        names.append("]");

        return names.toString();
    }
    public String getLeastValuable() {
        StringBuilder names = new StringBuilder("least: [");
        double amount = Double.parseDouble(String.valueOf(getValuableList().get(0).getValue()));
        FixedAsset fixedAsset;
        FixedAsset leastValuableAsset = null;

        for (Valuable valuable: getValuableList()) {
            try {fixedAsset = (FixedAsset) valuable;}
            catch (RuntimeException e) {continue;}

            if (amount > Double.parseDouble(String.valueOf(valuable.getValue()))) {
                amount = Double.parseDouble(String.valueOf(valuable.getValue()));
                leastValuableAsset=fixedAsset;
            }
        }
        assert leastValuableAsset != null;
        names.append(leastValuableAsset.getName()).append(": ").append(amount);
        names.append("]");

        return names.toString();
    }

    public String toRow(){
        return String.format("Name: %s |Owner: %s |List of Valuables: %s |Total Value: %s",
                getName(),
                getOwner(),
                getListOfNames(),
                getTotalValue());
    }
}
