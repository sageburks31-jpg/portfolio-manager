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
        for (Valuable valuable: valuableList) {
            value = value.add(valuable.getValue());
        }
        return value;
    }
    private String getListOfNames() {
        StringBuilder names = new StringBuilder("[");
        for (int i = 0; i <= getValuableList().size()-1; i++) {
            Valuable valuable = getValuableList().get(i);
            FixedAsset fixedAsset;
            try {
                fixedAsset = (FixedAsset) valuable;
            } catch (RuntimeException e) {
                continue;
            }
            names.append(fixedAsset.getName()).append(": ").append(fixedAsset.getValue());
            if (i != getValuableList().size()-1){
                names.append(", ");
            } else {
                names.append("]");
            }
        }
        return names.toString();
    }
    public String getMostValuable() {
        StringBuilder names = new StringBuilder("[");
        BigDecimal amount = new BigDecimal("0");
        FixedAsset fixedAsset = null;
        int index = 0;

        for (int i = 0; i <= getValuableList().size()-1; i++) {
            Valuable valuable = getValuableList().get(i);

            try {fixedAsset = (FixedAsset) valuable;}
            catch (RuntimeException e) {continue;}

            if (Double.parseDouble(String.valueOf(amount)) < Double.parseDouble(String.valueOf(fixedAsset.getValue()))) {
                index = i;
                amount=fixedAsset.getValue();
            }
        }
        try {fixedAsset = (FixedAsset) valuableList.get(index);}
        catch (RuntimeException e) {names = new StringBuilder((CharSequence) e);}
        assert fixedAsset != null;

        names.append(fixedAsset.getName()).append(": ").append(fixedAsset.getValue());
        names.append("]");
        return names.toString();
    }
    public String getLeastValuable() {
        StringBuilder names = new StringBuilder("[");
        BigDecimal amount = new BigDecimal("0");
        FixedAsset fixedAsset = null;
        int index = 0;

        for (int i = 0; i <= getValuableList().size()-1; i++) {
            Valuable valuable = getValuableList().get(i);

            try {fixedAsset = (FixedAsset) valuable;}
            catch (RuntimeException e) {continue;}

            if (Double.parseDouble(String.valueOf(amount)) > Double.parseDouble(String.valueOf(fixedAsset.getValue()))) {
                index = i;
                amount=fixedAsset.getValue();
            }
        }
        try {fixedAsset = (FixedAsset) valuableList.get(index);}
        catch (RuntimeException e) {names = new StringBuilder((CharSequence) e);}
        assert fixedAsset != null;

        names.append(fixedAsset.getName()).append(": ").append(fixedAsset.getValue());
        names.append("]");
        return names.toString();
    }

    public String toRow(){
        return String.format("%s | %s | %s | %s",
                getName(),
                getOwner(),
                getListOfNames(),
                getTotalValue());
    }
}
