package com.parcel.enums;

public enum ParcelType {

    HEAVY_PARCEL("Heavy Parcel"),
    SMALL_PARCEL("Small Parcel"),
    MEDIUM_PARCEL("Medium Parcel"),
    LARGE_PARCEL("Large Parcel");

    private final String value;

    public String getValue() {
        return value;
    }

    ParcelType(String value) {
        this.value = value;
    }
}


