package com.polafix.polafix.pojos;

import java.util.ArrayList;
import java.util.Objects;

public class Actor {

    public String actorId;
    public String name;
    public String surname;

    public ArrayList<Serie> series;

    public Actor(String actorId, String name, String surname, ArrayList<Serie> series) {
        this.actorId = actorId;
        this.name = name;
        this.surname = surname;
        setSeries(series);
    }
    
    public String getActorId() {
        return actorId;
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
        if (!(o instanceof Actor)) {
            return false;
        }
        Actor actor = (Actor) o;
        return Objects.equals(actorId, actor.actorId) && Objects.equals(name, actor.name) && Objects.equals(surname, actor.surname) && Objects.equals(series, actor.series);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actorId, name, surname, series);
    }
}
