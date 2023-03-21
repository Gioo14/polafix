package com.polafix.polafix.pojos;

import java.util.ArrayList;
import java.util.Objects;

public class Season {
    public String title;
    public Serie serie;
    public int number;
    public ArrayList<Chapter> chapters;

    public Season(String title, int number, Serie serie) {
        this.serie=serie;
        setTitle(title);
        setNumber(number);
        this.chapters = new ArrayList<Chapter>();
    }

    public String getTitle() {
        return this.title;
    }

    public int getNumber() {
        return this.number;
    }

    public ArrayList<Chapter> getChapters() {
        return this.chapters;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void addChapter(Chapter chapter) {
        if(!chapters.contains(chapter))
            this.chapters.add(chapter);
    }

    public Chapter getChapter(String title){
        for(int i=0; i<this.getChapters().size(); i++){
            if(this.getChapters().get(i).getTitle().equals(title)){
                return this.getChapters().get(i);
            }
        }
        return null;
    }

    public Chapter getChapter(int number){
        for(int i=0; i<this.getChapters().size(); i++){
            if(this.getChapters().get(i).getNumber()==number){
                return this.getChapters().get(i);
            }
        }
        return null;
    }

    public Serie getSerie(){
        return this.serie;
    }

    public boolean equals(Season season){
        if(this.serie.equals(season.serie) && (this.title.equals(season.title)) && (this.number==season.number))
            return true;
        else
            return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, serie, number, chapters);
    }

}