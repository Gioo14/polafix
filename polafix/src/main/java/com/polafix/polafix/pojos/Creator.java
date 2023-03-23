package com.polafix.polafix.pojos;

import java.util.ArrayList;
import java.util.Objects;

public class Creator {
    public String creatorId;
    public String name;
    public String surname;
    public ArrayList<Serie> series;

    public Creator(String creatorId, String name, String surname, ArrayList<Serie> series) {
        this.creatorId = creatorId;
        this.name = name;
        this.surname = surname;
        setSeries(series);
    }

    public String getCreatorId() {
        return creatorId;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Creator)) {
            return false;
        }
        Creator creator = (Creator) o;
        return Objects.equals(creatorId, creator.creatorId) && Objects.equals(name, creator.name) && Objects.equals(surname, creator.surname) && Objects.equals(series, creator.series);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creatorId, name, surname, series);
    }
   
}
