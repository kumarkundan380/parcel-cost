package com.parcel.enums;

public enum ResponseStatus {

    SUCCESS ("SUCCESS"),
    ERROR ("ERROR"),
    REJECT("REJECT");

    private final String value;

    public String getValue() {
        return value;
    }
    ResponseStatus(String value){
        this.value = value;
    }
}
