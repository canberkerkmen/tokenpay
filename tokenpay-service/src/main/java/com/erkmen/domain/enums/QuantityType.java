package com.erkmen.domain.enums;

import java.util.Arrays;

public enum QuantityType {

    NONE(-1, "-"),
    PIECE(1, "Adet"),
    LITRE(2, "Litre"),
    PACKET(3, "Paket");

    private final Integer code;
    private final String name;

    QuantityType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static QuantityType of(Integer code) {
        return Arrays.stream(QuantityType.values()).
                filter(quantityType -> quantityType.getCode().equals(code)).findFirst().orElse(NONE);
    }

    public static QuantityType ofName(String name) {
        return Arrays.stream(QuantityType.values()).
                filter(quantityType -> quantityType.getName().equalsIgnoreCase(name)).findFirst().orElse(NONE);
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}