package com.polafix.polafix.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Balance {
    public float balance;
    public ArrayList<Charge> charges=new ArrayList<Charge>();

    public Balance(float balance, ArrayList<Charge> charges) {
        this.balance = balance;
        this.charges = charges;
    }

    public float getSaldo() {
        return balance;
    }

    public void setSaldo(float amount){
        balance = amount;
    }

    public ArrayList<Charge> getAllCharges() {
        return charges;
    }

    public ArrayList<Charge> getCharges(int month, int year){
        ArrayList<Charge> charges = getAllCharges();
        ArrayList<Charge> chargesInDate = new ArrayList<Charge>();

        for(int i=0; i<charges.size(); i++){
            LocalDate date = charges.get(i).getDate();
            int anno = date.getYear();
            int mese = date.getMonthValue();
            if(year==anno && mese==month){
                chargesInDate.add(charges.get(i));
            }
        }
        return chargesInDate;
    }

    public void addCharge(Charge charge){
        this.getAllCharges().add(charge);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Balance)) {
            return false;
        }
        Balance b = (Balance) o;
        return balance == b.balance && Objects.equals(charges, b.charges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, charges);
    }

}
