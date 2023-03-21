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

    public Serie(String name, Type type, String shortDescription) {
        setName(name);
        setType(type);
        setDescription(shortDescription);
        this.seasons = new ArrayList<Season>();
        this.actors = new ArrayList<Actor>();
        this.creators = new ArrayList<Creator>();
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

    public void addActor(Actor actor) {
        if(!actors.contains(actor))
            this.actors.add(actor);
    }

    public void addCreator(Creator creator) {
        if(!creators.contains(creator))
            this.creators.add(creator);
    }

    public void addSeason(Season season) {
        if(!seasons.contains(season))
            this.seasons.add(season);
    }

    public void setSeasons(ArrayList<Season> stagioni){
        for(int i=0; i<stagioni.size(); i++){
            if(!seasons.contains(stagioni.get(i)))
                this.addSeason(stagioni.get(i));
        }
    }

    public void setActors(ArrayList<Actor> attori){
        for(int i=0; i<attori.size(); i++){
            if(!actors.contains(attori.get(i)))
                this.addActor(attori.get(i));
        }
    }

    public void setCreators(ArrayList<Creator> creatori){
        for(int i=0; i<creatori.size(); i++){
            if(!creators.contains(creatori.get(i)))
                this.addCreator(creatori.get(i));
        }
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
