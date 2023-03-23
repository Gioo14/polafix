package com.polafix.polafix.testPojos;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.polafix.polafix.pojos.Chapter;
import com.polafix.polafix.pojos.Season;
import com.polafix.polafix.pojos.Serie;
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

        assertEquals(0.00, utente.getBalance().getSaldo());
        assertEquals(0, utente.getBalance().getAllCharges().size());
    }

    @Test
    public void testSerie(){
        Serie lost = new Serie(serieName, typeSerie, description);
        Season lost1 = new Season("Lost1", 1, lost);
        Chapter lost1_1 = new Chapter(1, "lost1_1", description, lost1);
        Chapter lost1_2 = new Chapter(2, "lost1_2", description, lost1);
        Chapter lost1_3 = new Chapter(3, "lost1_3", description, lost1);
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
    public void aggiungiSerie(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(date_string);
        try {
            date = formatter.parse(date_string);
          } catch (ParseException e) {
            e.printStackTrace();
          }
        User utente = new User(email, type, IBAN, name, surname, date);

        Serie lost = new Serie(serieName, typeSerie, description);
        Season lost1 = new Season("Lost1", 1, lost);
        Chapter lost1_1 = new Chapter(1, "lost1_1", description, lost1);
        Chapter lost1_2 = new Chapter(2, "lost1_2", description, lost1);
        Chapter lost1_3 = new Chapter(3, "lost1_3", description, lost1);
        ArrayList<Chapter> chapters = new ArrayList<Chapter>();
        chapters.add(lost1_1);
        chapters.add(lost1_2);
        chapters.add(lost1_3);
        setSerie(lost, lost1, chapters);

        //User add a serie in his lists of series
        utente.addSerie(lost);
        assertEquals(1, utente.getInlist().size());
        assertEquals(0, utente.getEnded().size());
        assertEquals(0, utente.getStarted().size());

        //User add chapter visto
        
    }

}
