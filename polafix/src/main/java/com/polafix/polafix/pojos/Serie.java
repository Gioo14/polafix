package com.polafix.polafix.pojos;

import java.util.ArrayList;

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
}
