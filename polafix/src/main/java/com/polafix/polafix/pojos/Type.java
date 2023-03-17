package com.polafix.polafix.pojos;

public enum Type {
   
    GOLD(1.0f),
    SILVER(0.0f),
    STANDARD(2.0f);

    private final float price;

    private Type(float price) {
        this.price = price;
    }

    public float getprice() {
        return price;
    }
}
