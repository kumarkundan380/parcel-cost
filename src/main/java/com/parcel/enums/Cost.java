package com.parcel.enums;

import java.math.BigDecimal;

public enum Cost {

    HEAVY_PARCEL(new BigDecimal("20.0")),
    SMALL_PARCEL(new BigDecimal("0.03")),
    MEDIUM_PARCEL(new BigDecimal("0.04")),
    LARGE_PARCEL(new BigDecimal("0.05"));

    private final BigDecimal cost;

    public BigDecimal getCost() {
        return cost;
    }

    Cost(BigDecimal cost) {
        this.cost = cost;
    }
}
