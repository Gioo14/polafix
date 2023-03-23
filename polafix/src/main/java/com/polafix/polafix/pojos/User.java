package com.polafix.polafix.pojos;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class User {

    private String email;
    private String name;
    private String surname;
    private Subscripton type;
    private Date dateOfBirth;
    private String IBAN;
    private String password;

    private ArrayList<SerieUser> ended;
    private ArrayList<SerieUser> started;
    private ArrayList<SerieUser> inlist;
    private ArrayList<Balance> balances;
    
    public User(String email, Subscripton type, String IBAN, String name, String surname, Date dateOfBirth){
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.type=type;
        this.IBAN=IBAN;
        this.dateOfBirth = dateOfBirth;

        setPassword(password);

        this.started= new ArrayList<SerieUser>();
        this.ended= new ArrayList<SerieUser>();
        this.inlist= new ArrayList<SerieUser>();
        this.balances = new ArrayList<Balance>();
        Balance first = new Balance(0, LocalDate.now().getMonth(), LocalDate.now().getYear());
        this.balances.add(first);
    }

    public String getEmail() {
        return email;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public void setSurname(String surname){
        this.surname=surname;
    }

    public void setEmail(String email){
        this.email=email;
    }

    public Date getDateOfBirth(){
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date date){
        this.dateOfBirth=date;
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

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public ArrayList<SerieUser> getEnded() {
        return ended;
    }

    public ArrayList<SerieUser> getStarted() {
        return started;
    }

    public ArrayList<SerieUser> getInlist() {
        return inlist;
    }

    public ArrayList<Balance> getBalances() {
        return this.balances;
    }

    private Balance addBalance(Month month, int year) {
        float saldo = 0;
        Balance balance = new Balance(saldo, month, year);
        this.balances.add(balance);
        return balance;
    }

    public Balance getHistoryBalance(Month month, int year){
        ArrayList<Balance> balances = this.getBalances();
        for (Balance balance : balances) {
            Month mese = balance.getMonth();
            int anno = balance.getYear();
            if(year==anno && mese.equals(month)){
                return balance;
            }
        }
        return null;
    }

    public Balance getLastBalance(){
        return balances.get(balances.size()-1);
    }

    private void addCharge(Serie serie, Season season, Chapter chapter){
        LocalDate date = LocalDate.now();
        Month month = date.getMonth();
        int year = date.getYear();
        Balance lastBalance = getLastBalance();
        Charge charge = new Charge(date, serie.getName(), season.getNumber(), chapter.getNumber(), serie.type.getprice());
        if(lastBalance.getMonth().equals(month) && year==lastBalance.getYear()){
            lastBalance.addCharge(charge);
        }else{
            Balance balance = addBalance(month, year);
            balance.addCharge(charge);
        }
    } 
    
    private boolean isInList(Serie serie, ArrayList<SerieUser> lista){
        for(int i=0; i<lista.size(); i++){
            if(lista.get(i).getSerie().equals(serie)){
                return true;
            }
        }
        return false;
    }

    public void addSerie(Serie serie){
        if(isInList(serie, inlist)==false && isInList(serie, ended)==false && isInList(serie, started)==false){
            SerieUser serieuser = new SerieUser(serie, 1);
            inlist.add(serieuser);
        }
    }

    private SerieUser getSerieUser(ArrayList<SerieUser> lista, Serie serie){
        for(int i=0; i<lista.size(); i++){
            if(lista.get(i).getSerie().equals(serie)){
                return lista.get(i);
            }
        }
        return null; // sistema!!!
    } 

    private boolean isLastChapter(Serie serie, Season season, Chapter chapter){
        int lastSeason = serie.getSeasons().size();
        int lastChapter = season.getChapters().size();
        if(lastSeason==season.getNumber() && lastChapter==chapter.getNumber())
            return true;
        else
            return false;
    }

    public void selectChapter(Serie serie, Season season, Chapter chapter){
        addSerie(serie);
        if(isInList(serie, inlist)){
            SerieUser serieutente = getSerieUser(inlist, serie);
            serieutente.addChapterSeen(chapter);
            serieutente.setCurrentSeason(season.getNumber());
            this.addCharge(serie, season, chapter);
            if(isLastChapter(serie, season, chapter)){
                ended.add(serieutente);
                inlist.remove(serieutente);
                serieutente.setCurrentSeason(1);
            }
            else{
                started.add(serieutente);
                inlist.remove(serieutente);
            }  
        }
        else{
            if(isInList(serie, started)){
                SerieUser serieutente = getSerieUser(started, serie);
                serieutente.addChapterSeen(chapter);
                serieutente.setCurrentSeason(season.getNumber());
                this.addCharge(serie, season, chapter);
                if(isLastChapter(serie, season, chapter)){
                    ended.add(serieutente);
                    started.remove(serieutente);
                    serieutente.setCurrentSeason(1);
                }
            }
            else{
                SerieUser serieutente = getSerieUser(ended, serie);
                serieutente.addChapterSeen(chapter);
                serieutente.setCurrentSeason(season.getNumber());
                this.addCharge(serie, season, chapter);
            }
        }
    }

    public Chapter viewLastChapter(SerieUser serie){
        Chapter chapter = serie.getLastChapter();
        if(chapter==null){
            return serie.getSerie().getSeason(1).getChapter(1);
        }else
            return chapter;
    }

    public SerieUser viewSerieUser(ArrayList<SerieUser> userList, String nameSerie){
        for (SerieUser serieUtente : userList) {
            if(serieUtente.getSerie().getName().equals(nameSerie))
                return serieUtente;
        }
        return null; //Sistemare!!
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, surname, type, dateOfBirth, IBAN, password, ended, started, inlist, balances);
    }
}
