package com.polafix.polafix.testPojos;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import com.polafix.polafix.pojos.Balance;
import com.polafix.polafix.pojos.Chapter;
import com.polafix.polafix.pojos.Charge;
import com.polafix.polafix.pojos.Season;
import com.polafix.polafix.pojos.Serie;
import com.polafix.polafix.pojos.SerieUser;
import com.polafix.polafix.pojos.Subscripton;
import com.polafix.polafix.pojos.Type;
import com.polafix.polafix.pojos.User;

public class test {
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Serie> series = new ArrayList<Serie>();

    private String email = "anna.bianchi@gmail.com";
    private Subscripton type = Subscripton.NOTSUBSCRIBED;
    private String name ="ANNA";
    private String surname = "BIANCHI";
    private String IBAN = "xxxxxxxxxxxxx";
    private String date_string = "20/09/1990";

    private String idSerie = "@seriexx";
    private String serieName = "Lost";
    private Type typeSerie = Type.SILVER;
    private String description = "....";

    public void setSerie(Serie lost, Season lost1, ArrayList<Chapter> chapters){
        for(int i=0; i<chapters.size(); i++){
            lost1.addChapter(chapters.get(i));
        }
        lost.addSeason(lost1);
    }

    public boolean addUser(User utente){
        if(users.isEmpty()){
            users.add(utente);
            return true;
        }
        else{
            if(users.contains(utente)){
                System.out.println("Utente già nel sistema"); 
                return false;
            }
            else{
                users.add(utente);
                return true;
        }
    }
    }

    public boolean addSerie(Serie serie){
        if(series.isEmpty()){
            series.add(serie);
            return true;
        }
        else{
            if(series.contains(serie)){
                System.out.println("Serie già nel catalogo"); 
                return false;
            }
            else{
                series.add(serie);
                return true;
        }
    }
    }

    //Test on the singole method of the User class to see if it's implemented well
    @Test
    public void testUser(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(date_string);
        try {
            date = formatter.parse(date_string);
          } catch (ParseException e) {
            e.printStackTrace();
          }
        User utente = new User(email, type, IBAN, name, surname, date);

        assertEquals(true, addUser(utente));
        assertEquals("anna.bianchi@gmail.com" ,utente.getEmail());
        assertEquals(Subscripton.NOTSUBSCRIBED, utente.getType());

        assertEquals(0, utente.getInlist().size());
        assertEquals(0, utente.getEnded().size());
        assertEquals(0, utente.getStarted().size());
    }

    //Test on the singole method of the Serie class to see if it's implemented well
    @Test
    public void testSerie(){
        Serie lost = new Serie(idSerie, serieName, typeSerie, description);
        Season lost1 = new Season("Lost1", 1);
        Chapter lost1_1 = new Chapter(1, "lost1_1", description);
        Chapter lost1_2 = new Chapter(2, "lost1_2", description);
        Chapter lost1_3 = new Chapter(3, "lost1_3", description);
        ArrayList<Chapter> chapters = new ArrayList<Chapter>();
        chapters.add(lost1_1);
        chapters.add(lost1_2);
        chapters.add(lost1_3);

        setSerie(lost, lost1, chapters);
    
        assertEquals(true, addSerie(lost));
        assertEquals("Lost", lost.getName());
        assertEquals(Type.SILVER, lost.getType());

        assertEquals(1, lost.getSeasons().size());
        assertEquals(3, lost.getSeason(1).getChapters().size());
        assertEquals("lost1_1", lost.getSeason(1).getChapter(1).getTitle());
        assertEquals("lost1_2", lost.getSeason(1).getChapter(2).getTitle());
        assertEquals("lost1_3", lost.getSeason(1).getChapter(3).getTitle());
    }

    @Test
    public void addSerie(){
        //Create a new user
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(date_string);
        try {
            date = formatter.parse(date_string);
          } catch (ParseException e) {
            e.printStackTrace();
          }
        User utente = new User(email, type, IBAN, name, surname, date);

        //Create a new serie
        Serie lost = new Serie(idSerie, serieName, typeSerie, description);
        Season lost1 = new Season("Lost1", 1);
        Chapter lost1_1 = new Chapter(1, "lost1_1", description);
        Chapter lost1_2 = new Chapter(2, "lost1_2", description);
        Chapter lost1_3 = new Chapter(3, "lost1_3", description);
        ArrayList<Chapter> chapters = new ArrayList<Chapter>();
        chapters.add(lost1_1);
        chapters.add(lost1_2);
        chapters.add(lost1_3);
        setSerie(lost, lost1, chapters);

        //User add a serie in his lists of series
        utente.addSerie(lost);
        SerieUser serieUtente = new SerieUser(lost, 1);
        assertEquals(1, utente.getInlist().size());
        assertEquals(serieUtente, utente.getInlist().get(0));

        //User select a chapter: the chapter state change and the chapter is added at the balance
        utente.selectChapter(lost, lost1, lost1_1);
        serieUtente.addChapterSeen(lost1_1);
        Charge expected = new Charge(LocalDate.now(), lost.getName(), 1, 1, lost.getType().getprice());
        assertEquals(1, utente.getStarted().size());
        assertEquals(1, utente.getLastBalance().getAllCharges().size());
        assertEquals(expected, utente.getLastBalance().getAllCharges().get(0));
        assertEquals(serieUtente, utente.getStarted().get(0));
        assertEquals(serieUtente, utente.viewSerieUser(utente.getStarted(), "Lost"));
        
        //User visualize the serie from the list
        Chapter c = utente.viewLastChapter(serieUtente);
        assertEquals(lost1_2, c);

        //View last chapter -> verify ended
        utente.selectChapter(lost, lost1, lost1_3);
        serieUtente.addChapterSeen(lost1_3);
        expected = new Charge(LocalDate.now(), lost.getName(), 1, 3, lost.getType().getprice());
        assertEquals(1, utente.getEnded().size());
        assertEquals(serieUtente, utente.getEnded().get(0));

        //Verify started and inlist are empty
        assertEquals(0, utente.getStarted().size());
        assertEquals(0, utente.getInlist().size());

        assertEquals(1.50, utente.getLastBalance().getAmount());
    }

    @Test
    public void testBalances(){
        //Create a new user
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(date_string);
        try {
            date = formatter.parse(date_string);
          } catch (ParseException e) {
            e.printStackTrace();
          }
        User utente = new User(email, type, IBAN, name, surname, date);

        //Create a new serie
        Serie lost = new Serie(idSerie, serieName, typeSerie, description);
        Season lost1 = new Season("Lost1", 1);
        Chapter lost1_1 = new Chapter(1, "lost1_1", description);
        Chapter lost1_2 = new Chapter(2, "lost1_2", description);
        Chapter lost1_3 = new Chapter(3, "lost1_3", description);
        ArrayList<Chapter> chapters = new ArrayList<Chapter>();
        chapters.add(lost1_1);
        chapters.add(lost1_2);
        chapters.add(lost1_3);
        setSerie(lost, lost1, chapters);

        //Verify different balances
        LocalDate gennaio = LocalDate.of(2023, 01, 05);
        LocalDate febbraio = LocalDate.of(2023, 02, 21);
        Charge chargeGennaio = new Charge(gennaio, lost.getName(), 1, 1, lost.getType().getprice());
        Charge chargeFebbraio = new Charge(gennaio, lost.getName(), 1, 2, lost.getType().getprice());
        Balance bg = new Balance(0, gennaio.getMonth(), gennaio.getYear());
        Balance bf = new Balance(0, febbraio.getMonth(), febbraio.getYear());
        bg.addCharge(chargeGennaio);
        bf.addCharge(chargeFebbraio);
        utente.getBalances().add(bg);
        utente.getBalances().add(bf);

        assertEquals(bg, utente.getHistoryBalance(gennaio.getMonth(), gennaio.getYear()));
        assertEquals(chargeGennaio,  utente.getHistoryBalance(gennaio.getMonth(), gennaio.getYear()).getAllCharges().get(0));
        assertEquals(0.75, bg.getAmount());
        assertEquals(bf, utente.getHistoryBalance(febbraio.getMonth(), febbraio.getYear()));
        assertEquals(chargeFebbraio,  utente.getHistoryBalance(febbraio.getMonth(), febbraio.getYear()).getAllCharges().get(0));
        assertEquals(0.75, bf.getAmount());
    }

}
