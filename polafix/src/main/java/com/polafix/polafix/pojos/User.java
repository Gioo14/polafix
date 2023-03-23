package com.polafix.polafix.pojos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class User {
    public String email;
    public String name;
    public String surname;
    public Subscripton type;
    public Date dateOfBirth;
    public String IBAN;
    private String password;
    public ArrayList<SerieUser> ended;
    public ArrayList<SerieUser> started;
    public ArrayList<SerieUser> inlist;
    public Balance balance;

    public User(String email, Subscripton type, String IBAN, String name, String surname, Date dateOfBirth){
        this.name=name;
        this.surname=surname;
        this.email=email;
        this.type=type;
        this.IBAN=IBAN;
        this.dateOfBirth = dateOfBirth;
        
        this.balance = setBalance();
        setPassword(password);

        this.started= new ArrayList<SerieUser>();
        this.ended= new ArrayList<SerieUser>();
        this.inlist= new ArrayList<SerieUser>();
    }

    public String getEmail() {
        return email;
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

    public Balance getBalance() {
        return balance;
    }

    public Balance setBalance() {
        float saldo = 0;
        ArrayList<Charge> charges = new ArrayList<>();
        Balance balance = new Balance(saldo, charges);
        return balance;
    }

    private void addCharge(Serie serie, Season season, Chapter chapter){
        LocalDate date = LocalDate.now();
        Charge charge = new Charge(date, serie.getName(), season.getNumber(), chapter.getNumber(), serie.type.getprice());
        this.getBalance().addCharge(charge);
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

    private SerieUser getSerieUtente(ArrayList<SerieUser> lista, Serie serie){
        for(int i=0; i<lista.size(); i++){
            if(lista.get(i).getSerie().equals(serie)){
                return lista.get(i);
            }
        }
        return null; // sistema!!!
    }

    public void selectChapter(Serie serie, Season season, Chapter chapter){
        addSerie(serie);
        if(isInList(serie, inlist)){
            SerieUser serieutente = getSerieUtente(inlist, serie);
            serieutente.addChapterSeen(chapter);
            serieutente.setCurrentSeason(season.getNumber());
            this.addCharge(serie, season, chapter);
            started.add(serieutente);
            inlist.remove(serieutente);
        }
        else{
            if(isInList(serie, started)){
                SerieUser serieutente = getSerieUtente(started, serie);
                serieutente.addChapterSeen(chapter);
                serieutente.setCurrentSeason(season.getNumber());
                this.addCharge(serie, season, chapter);
                if(serieutente.getLastChapter()==null){
                    serieutente.setCurrentSeason(1);
                    /*Reinizializzo tutti i chapter a "Pendiente"

                    for (ChapterSeen c : serieutente.getUserChapters()) {
                        c.setState(ChapterState.PIENDENTE);
                    }
                    */
                    ended.add(serieutente);
                    started.remove(serieutente);
                }
            }
            else{
                /*
                    Devo anche ripagare?
                    this.addCharge(serie, season, chapter);
                    ........
                    Rimetto la serie in started
                    serieutente.addChapterSeen(chapter);
                    serieutente.setCurrentSeason(season.getNumber());
                    started.add(serieutente);
                    ended.remove(serieutente);
                    ........
                 */
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

    public SerieUser viewSerieUtente(ArrayList<SerieUser> userList, String nameSerie){
        for (SerieUser serieUtente : userList) {
            if(serieUtente.getSerie().getName().equals(nameSerie))
                return serieUtente;
        }
        return null; //Sistemare!!
    }

}
