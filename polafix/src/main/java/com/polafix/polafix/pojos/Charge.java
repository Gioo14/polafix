package com.polafix.polafix.pojos;

import java.util.Date;

public class Charge {
    public Date date;
    public String name;
    public int season;
    public int number;
    public float price;

    public Charge(Date date, String name, int season, int number, float price) {
        this.date = date;
        this.name = name;
        this.season = season;
        this.number = number;
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public int getSeason() {
        return season;
    }

    public int getNumber() {
        return number;
    }

    public float getPrice() {
        return price;
    }


}
