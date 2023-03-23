package com.polafix.polafix.pojos;

import java.sql.Date;
import java.util.ArrayList;

public class ManagePaiment {
    public Date date;
    public ArrayList<User> users;

    public ManagePaiment(Date date, ArrayList<User> users) {
        this.date = date;
        this.users = users;
    }

    private User getUser(String userId){
        for(int i=0; i<users.size(); i++){
            if(this.users.get(i).getEmail().equals(userId)){
                User user = this.users.get(i);
                return user;
            }
        }
        return null;
    }

    public void managePaiment(String userId){
       User user = getUser(userId);
       Balance balance = user.getBalance();
       String iban = user.getIBAN();
       if(user.getType().equals(Subscripton.SUBSCRIBED)){
            System.out.print("Estrai 20 euro dal conto IBAN dell'utente: " + iban);
            balance.setSaldo(0);
       }
       else if(user.getType().equals(Subscripton.NOTSUBSCRIBED)){
            float saldo = balance.getSaldo();
            System.out.print("Estrai "+ saldo +" euro dal conto IBAN dell'utente: " + iban);
            balance.setSaldo(0);
        }
    }
}
