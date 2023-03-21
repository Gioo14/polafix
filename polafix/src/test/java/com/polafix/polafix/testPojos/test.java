package com.polafix.polafix.testPojos;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import com.polafix.polafix.pojos.Chapter;
import com.polafix.polafix.pojos.Season;
import com.polafix.polafix.pojos.Serie;
import com.polafix.polafix.pojos.Subscripton;
import com.polafix.polafix.pojos.Type;
import com.polafix.polafix.pojos.User;

public class test {
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<Serie> series = new ArrayList<Serie>();

    private String userId = "AABB1122";
    private Subscripton type = Subscripton.NOTSUBSCRIBED;
    private String name ="ANNA";
    private String surname = "BIANCHI";
    private String IBAN = "xxxxxxxxxxxxx";

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
        User utente = new User(userId, type, IBAN, name, surname);

        assertEquals(true, addUser(utente));
        assertEquals("AABB1122" ,utente.getUserID());
        assertEquals(Subscripton.NOTSUBSCRIBED, utente.getType());
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
    }

    @Test
    public void watchSerie(){
        User user = users.get(0);
        Serie serie = series.get(0);
    }

}
