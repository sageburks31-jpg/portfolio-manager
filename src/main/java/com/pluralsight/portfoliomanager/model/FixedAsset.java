package com.pluralsight.portfoliomanager.model;

import java.math.BigDecimal;

public abstract class FixedAsset implements Valuable{
    private final String name;
    private final BigDecimal marketValue;

    public FixedAsset(String name, BigDecimal marketValue) {
        this.name = name;
        this.marketValue = marketValue;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return marketValue;
    }
}
