package com.polafix.polafix.pojos;

import java.util.ArrayList;
import java.util.Objects;

public class Serie {
    public String name;
    public Type type;
    public String shortDescription;
    public ArrayList<Season> seasons;
    public ArrayList<Actor> actors;
    public ArrayList<Creator> creators;

    public Serie(String name, Type type, String shortDescription, ArrayList<Season> seasons, ArrayList<Actor> actors, ArrayList<Creator> creators) {
        setName(name);
        setType(type);
        setDescription(shortDescription);
        setSeasons(seasons);
        setActors(actors);
        setCreators(creators);
    } 

    public String getName() {
        return this.name;
    }

    public Type getType() {
        return type;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }

    public ArrayList<Actor> getActors() {
        return actors;
    }

    public ArrayList<Creator> getCreators() {
        return creators;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setActors(ArrayList<Actor> actors) {
        this.actors = new ArrayList<Actor>(actors);
    }

    public void setCreators(ArrayList<Creator> creators) {
        this.creators = new ArrayList<Creator>(creators);
    }

    public void setSeasons(ArrayList<Season> seasons) {
        this.seasons= new ArrayList<Season>(seasons);
    }
    
    public Season getSeason(String title){
        for(int i=0; i<this.getSeasons().size(); i++){
            if(this.getSeasons().get(i).getTitle().equals(title)){
                return this.getSeasons().get(i);
            }
        }
        return null;
    }

    public Chapter getChapter(Season season, String title){
        return season.getChapter(title);
    }

    public Season getSeason(int number){
        for(int i=0; i<this.getSeasons().size(); i++){
            if(this.getSeasons().get(i).getNumber()==number){
                return this.getSeasons().get(i);
            }
        }
        return null;
    }

    public Chapter getChapter(Season season, int number){
        return season.getChapter(number);
    }

    public boolean equals(Serie serie){
        if(this.name.equals(serie.name) && equalsActors(serie.getActors()) && equalsCreators(serie.getCreators()))
            return true;
        else
            return false;
    }

    private boolean equalsActors(ArrayList<Actor> actors){
        for(int i=0; i<actors.size(); i++){
            if(!this.actors.contains(actors.get(i)))
                return false;
        }
        return true;
    }

    private boolean equalsCreators(ArrayList<Creator> creators){
        for(int i=0; i<creators.size(); i++){
            if(!this.creators.contains(creators.get(i)))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, shortDescription, seasons, actors, creators);
    }
}
