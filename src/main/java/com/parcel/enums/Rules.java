package com.parcel.enums;

public enum Rules {

    HEAVY_PARCEL("Heavy Parcel"),
    SMALL_PARCEL("Small Parcel"),
    MEDIUM_PARCEL("Medium Parcel"),
    LARGE_PARCEL("Large Parcel");

    private final String value;

    public String getValue() {
        return value;
    }

    Rules(String value) {
        this.value = value;
    }
}


