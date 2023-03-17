package com.polafix.polafix.pojos;

import java.util.ArrayList;

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

}
