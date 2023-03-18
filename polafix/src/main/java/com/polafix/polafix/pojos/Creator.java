package com.polafix.polafix.pojos;

import java.util.ArrayList;
import java.util.Objects;

public class Creator {
    public String name;
    public String surname;
    public ArrayList<Serie> series;
   
    public Creator(String name, String surname, ArrayList<Serie> series) {
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

    public boolean equals(Creator creator){
        if(this.name.equals(creator.name) && this.surname.equals(creator.surname))
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, series);
    }
}
