package com.polafix.polafix.pojos;

import java.util.Objects;

public class Chapter {
    public int number;
    public String title;
    public String description;
    public Season season;
    public Serie serie;

    public Chapter(int number, String title, String description, Season season) {
        this.season=season;
        this.serie=season.getSerie();
        setNumber(number);
        setTitle(title);
        setDescription(description);
    }

    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Season getSeason() {
        return season;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Serie getSerie(){
        return season.getSerie();
    }

    public boolean equals(Chapter chapter){
        if(this.title.equals(chapter.title) && this.number==(chapter.number) && this.season.equals(chapter.season))
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, title, description, season, serie);
    }
}
