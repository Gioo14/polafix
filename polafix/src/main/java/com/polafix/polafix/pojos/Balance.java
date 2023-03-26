package com.polafix.polafix.pojos;

import java.time.Month;
import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float amount;
    private Month month;
    private int year;
    private ArrayList<Charge> charges;

    public Balance(float amount, Month month, int year) {
        this.month = month;
        this.year = year;
        this.amount = amount;
        this.charges = new ArrayList<Charge>();
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount){
        this.amount = amount;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<Charge> getAllCharges() {
        return charges;
    }

    public void addCharge(Charge charge){
        this.getAllCharges().add(charge);
        float newAmount = this.getAmount() + charge.getPrice();
        setAmount(newAmount);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Balance)) {
            return false;
        }
        Balance balance = (Balance) o;
        return Objects.equals(month, balance.month) && year == balance.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, year);
    }
}
