package com.polafix.polafix.pojos;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class User {

    @Id
    private String email;
    private String name;
    private String surname;
    private Subscription type;
    private Date dateOfBirth;
    private String IBAN;
    private String password;
    @OneToMany(fetch = FetchType.EAGER)
    private List<SerieUser> ended;
    @OneToMany(fetch = FetchType.EAGER)
    private List<SerieUser> started;
    @OneToMany(fetch = FetchType.EAGER)
    private List<SerieUser> inlist;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Balance> balances;

    public User() {}
    
    public User(String email, Subscription type, String IBAN, String name, String surname, Date dateOfBirth){
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

    public Subscription getType() {
        return this.type;
    }

    public void setType(Subscription type) {
        this.type = type;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public List<SerieUser> getEnded() {
        return ended;
    }

    public List<SerieUser> getStarted() {
        return started;
    }

    public List<SerieUser> getInlist() {
        return inlist;
    }

    public List<Balance> getBalances() {
        return this.balances;
    }

    private Balance addBalance(Month month, int year) {
        float saldo = 0;
        Balance balance = new Balance(saldo, month, year);
        this.balances.add(balance);
        return balance;
    }

    public Balance getHistoryBalance(Month month, int year){
        List<Balance> balances = this.getBalances();
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

    private void addCharge(SerieUser serie, int season, int chapter){
        LocalDate date = LocalDate.now();
        Month month = date.getMonth();
        int year = date.getYear();
        Balance lastBalance = getLastBalance();
        Charge charge = new Charge(date, serie.getSerie().getName(), season, chapter, serie.getSerie().getType().getprice());
        if(lastBalance.getMonth().equals(month) && year==lastBalance.getYear()){
            lastBalance.addCharge(charge);
        }else{
            Balance balance = addBalance(month, year);
            balance.addCharge(charge);
        }
    } 
    
    private boolean isInListUser(SerieUser serie, List<SerieUser> lista){
        for(SerieUser s : lista){
            if(s.getSerie().getName().equals(serie.getSerie().getName())){
                return true;
            }
        }
        return false;
    }

    private boolean isInList(Serie serie, List<SerieUser> lista){
        for(SerieUser s : lista){
            if(s.getSerie().getName().equals(serie.getName())){
                return true;
            }
        }
        return false;
    }

    public void addSerie(Serie serie){
        if(isInList(serie, inlist)==false && isInList(serie, ended)==false && isInList(serie, started)==false){
            SerieUser serieuser = new SerieUser(serie);
            inlist.add(serieuser);
        }
    }

    private boolean isLastChapter(SerieUser serie, ChapterSeen chapter){
        return serie.isLastChapter(chapter);
    }

    private SerieUser getSerieUser(List<SerieUser> lista, SerieUser serie){
        for (SerieUser serieUser : lista) {
            if(serieUser.getSerie().equals(serie.getSerie()))
                return serieUser;
        }
        return null;
    }

    public void selectChapter(SerieUser s, int season, int chapter){
        if(isInListUser(s, inlist)){
            SerieUser serie = getSerieUser(inlist, s);
            ChapterSeen c = serie.findChapter(serie.getUserChapters(), season, chapter);
            serie.addChapterSeen(season, chapter);
            int num_chapters = serie.getSerie().getSeason(season).getChapters().size();
            serie.setNextSeason(c, num_chapters);
            this.addCharge(serie, season, chapter);
            if(isLastChapter(serie, c)){
                inlist.remove(serie);
                ended.add(serie);
            }
            else{
                inlist.remove(serie);
                started.add(serie);    
            }  
        }
        else{
            if(isInListUser(s, started)){
                SerieUser serie = getSerieUser(started, s);
                ChapterSeen c = serie.findChapter(serie.getUserChapters(), season, chapter);
                serie.addChapterSeen(season, chapter);
                int num_chapters = serie.getSerie().getSeason(season).getChapters().size();
                serie.setNextSeason(c, num_chapters);
                this.addCharge(serie, season, chapter);
                if(isLastChapter(serie, c)){
                    ended.add(serie);
                    started.remove(serie);
                }
            }
            else{
                SerieUser serie = getSerieUser(ended, s);
                serie.addChapterSeen(season, chapter);
                ChapterSeen c = serie.findChapter(serie.getUserChapters(), season, chapter);
                int num_chapters = serie.getSerie().getSeason(season).getChapters().size();
                serie.setNextSeason(c, num_chapters);
                this.addCharge(serie, season, chapter);
            }
        }
    }

    public SerieUser viewSerieUser(List<SerieUser> userList, String nameSerie){
        for (SerieUser serieUtente : userList) {
            if(serieUtente.getSerie().getName().equals(nameSerie))
                return serieUtente;
        }
        return null;
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
        return Objects.hash(email);
    }

}