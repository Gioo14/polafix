package com.polafix.polafix.pojos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Balance {
    public float saldo;
    public ArrayList<Charge> charges=new ArrayList<Charge>();

    public Balance(float saldo, ArrayList<Charge> charges) {
        this.saldo = saldo;
        this.charges = charges;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float amount){
        saldo = amount;
    }

    public ArrayList<Charge> getAllCharges() {
        return charges;
    }

    public ArrayList<Charge> getCharges(int month, int year){
        ArrayList<Charge> charges = getAllCharges();
        ArrayList<Charge> chargesInDate = new ArrayList<Charge>();
        Calendar calendario = Calendar.getInstance();

        for(int i=0; i<charges.size(); i++){
            Date date = charges.get(i).getDate();
            calendario.setTime(date);
            int mese = calendario.get(Calendar.MONTH) + 1; //il mese è indicizzato da 0 a 11
            int anno = calendario.get(Calendar.YEAR);
            if(year==anno && mese==month){
                chargesInDate.add(charges.get(i));
            }
        }
        return chargesInDate;
    }

    public void addCharge(Charge charge){
        this.getAllCharges().add(charge);
    }

}
