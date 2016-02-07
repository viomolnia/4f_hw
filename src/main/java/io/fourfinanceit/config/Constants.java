package io.fourfinanceit.config;

/**
 * Created by Anna on 07.02.2016.
 */
public enum Constants {

    MinAmount("50"),
    MaxAmount1("300"),
    MaxAmount2("425"),

    MinTerm("10"),
    MaxTerm("30");

    String value;

    Constants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
