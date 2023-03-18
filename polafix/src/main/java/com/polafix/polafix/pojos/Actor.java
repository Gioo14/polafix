package com.polafix.polafix.pojos;

import java.util.ArrayList;
import java.util.Objects;

public class Actor {

    public String name;
    public String surname;
    public ArrayList<Serie> series;

    public Actor(String name, String surname, ArrayList<Serie> series) {
        this.name = name;
        this.surname = surname;
        setSeries(series);
    }
    
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public ArrayList<Serie> getSeries() {
        return series;
    }
    public void setSeries(ArrayList<Serie> series) {
        this.series = new ArrayList<Serie>(series);
    }
    public void addSerie(Serie serie){
        this.getSeries().add(serie);
    }

    public boolean equals(Actor actor){
        if(this.name.equals(actor.name) && this.surname.equals(actor.surname))
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, series);
    }
}
