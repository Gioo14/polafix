package com.polafix.polafix.pojos;

import java.util.Date;
import java.util.Objects;

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

    public boolean equals(Charge charge){
        if(this.date.equals(charge.date) && this.name.equals(charge.name) 
            && this.number==charge.number && this.price==charge.price && this.season==charge.season)
                return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, name, season, number, price);
    }

}
