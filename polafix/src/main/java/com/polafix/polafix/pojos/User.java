package com.polafix.polafix.pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class User {
    public String userID;
    public String name;
    public String surname;
    public Subscripton type;
    public String IBAN;
    private String password;
    public ArrayList<SerieUtente> ended;
    public ArrayList<SerieUtente> started;
    public ArrayList<SerieUtente> inlist;
    public Balance balance;

    public User(String userId, Subscripton type, String IBAN, String name, String surname){
        this.name=name;
        this.surname=surname;
        this.userID=userId;
        this.type=type;
        this.IBAN=IBAN;
        
        this.balance = setBalance();
        setPassword(password);

        this.started= new ArrayList<SerieUtente>();
        this.ended= new ArrayList<SerieUtente>();
        this.inlist= new ArrayList<SerieUtente>();
    }

    public boolean equals(User user){
        if(this.userID.equals(user.userID))
            return true;
        else
            return false;
    }

    public String getUserID() {
        return userID;
    }

    private void setPassword(String password){
        this.password=password;
    }

    public Subscripton getType() {
        return this.type;
    }

    public void setType(Subscripton type) {
        this.type = type;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String iBAN) {
        IBAN = iBAN;
    }

    public ArrayList<SerieUtente> getEnded() {
        return ended;
    }

    public ArrayList<SerieUtente> getStarted() {
        return started;
    }

    public ArrayList<SerieUtente> getInlist() {
        return inlist;
    }

    public Balance getBalance() {
        return balance;
    }

    public Balance setBalance() {
        float saldo = 0;
        ArrayList<Charge> charges = new ArrayList<>();
        Balance balance = new Balance(saldo, charges);
        return balance;
    }

    public void addCharge(Serie serie, Season season, Chapter chapter, Date date){
        Charge charge = new Charge(date, chapter.getTitle(), season.getNumber(), chapter.getNumber(), serie.type.getprice());
        this.getBalance().addCharge(charge);
    } 
    
    private boolean isInList(Serie serie, ArrayList<SerieUtente> lista){
        for(int i=0; i<lista.size(); i++){
            if(lista.get(i).getSerie().equals(serie)){
                return true;
            }
        }
        return false;
    }

    public void addSerie(Serie serie){
        if(!isInList(serie, inlist) && !isInList(serie, ended) && !isInList(serie, started)){
            SerieUtente serieuser = new SerieUtente(serie, 1);
            inlist.add(serieuser);
        }
    }

    private SerieUtente getSerieUtente(ArrayList<SerieUtente> lista, Serie serie){
        for(int i=0; i<lista.size(); i++){
            if(lista.get(i).getSerie().equals(serie)){
                return lista.get(i);
            }
        }
        return null;
    }

    public void selectChapter(Serie serie, Season season, Chapter chapter, Date date){
        addSerie(serie);
        if(isInList(serie, inlist)){
            SerieUtente serieutente = getSerieUtente(inlist, serie);
            serieutente.addChapterVisto(chapter);
            serieutente.setCurrentSeason(season.getNumber());
            started.add(serieutente);
            inlist.remove(serieutente);
        }
        if(isInList(serie, started)){
            SerieUtente serieutente = getSerieUtente(started, serie);
            serieutente.addChapterVisto(chapter);
            serieutente.setCurrentSeason(season.getNumber());
            this.addCharge(serie, season, chapter, date);
            if(serieutente.getLastChapter()==null){
                serieutente.setCurrentSeason(1);
                ended.add(serieutente);
                started.remove(serieutente);
            }
        }
    }

    public Chapter viewSerie(SerieUtente serie){
        Chapter chapter = serie.getLastChapter();
        if(chapter==null){
            return serie.getSerie().getSeason(1).getChapter(1);
        }else
            return chapter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, name, surname, type, IBAN, password, ended, started, inlist, balance);
    }
}
