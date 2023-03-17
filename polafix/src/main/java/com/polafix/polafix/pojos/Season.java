package com.polafix.polafix.pojos;

import java.util.ArrayList;

public class Season {
    public String title;
    public int number;
    public ArrayList<Chapter> chapters;

    public Season(String title, int number, ArrayList<Chapter> chapters) {
        setTitle(title);
        setNumber(number);
        setChapters(chapters);
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

    public void setChapters(ArrayList<Chapter> chapters) {
        this.chapters = new ArrayList<Chapter>(chapters);
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
}