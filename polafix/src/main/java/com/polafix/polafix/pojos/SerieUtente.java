package com.polafix.polafix.pojos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class SerieUtente {
    public Serie serie;
    public int currentSeason;

    public HashMap<Chapter, ChapterState> userChapters;

    public SerieUtente(Serie serie, int currentSeason) {
        this.serie = serie;
        this.currentSeason = currentSeason;
        setChapters(serie);
        //this.userChapters = userChapters;
    }

    public Serie getSerie() {
        return serie;
    }

    public int getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(int currentSeason) {
        this.currentSeason = currentSeason;
    }

    public HashMap<Chapter, ChapterState> getUserChapters() {
        return userChapters;
    }

    public void setChapters(Serie serie){
        HashMap<Chapter, ChapterState> userChapters = new HashMap<Chapter, ChapterState>();
        ArrayList<Season> seasons = serie.getSeasons();
        for(int i=0; i<seasons.size(); i++){
            ArrayList<Chapter> chapters = seasons.get(i).getChapters();
            for(int j=0; j<chapters.size(); j++){
                userChapters.put(chapters.get(j), ChapterState.PIENDENTE);
            }
        }
    }

    public void addChapterVisto(Chapter chapter){
        HashMap<Chapter, ChapterState> chapters = this.getUserChapters();
        chapters.replace(chapter, ChapterState.VISTO);
    }

    public Chapter getLastChapter(){
        Chapter c = null;
        int currentseason = this.getCurrentSeason();
        HashMap<Chapter, ChapterState> userChapters = this.getUserChapters();
        Season season = this.getSerie().getSeason(currentseason);
        ArrayList<Chapter> chapters = season.getChapters();
        for(int i=0; i<chapters.size(); i++){
            c = chapters.get(i);
            if(userChapters.get(c).equals(ChapterState.PIENDENTE)){
                return c;
            }
        }
        return c;
    }

    
    public boolean equals(SerieUtente serieUt){
        if(this.serie.equals(serieUt.serie) && this.currentSeason==serieUt.currentSeason && chaptersEquals(serieUt.getUserChapters()))
            return true;
        else
            return false;
    }

    private boolean chaptersEquals(HashMap<Chapter, ChapterState> chapters){
        for (Map.Entry<Chapter, ChapterState> entry : chapters.entrySet()) {
            Chapter chapter1 = entry.getKey();
            ChapterState state1 = entry.getValue();
            if(!this.userChapters.containsKey(chapter1))
                return false;
            else{
                ChapterState state2 = this.userChapters.get(chapter1);
                if(!state2.equals(state1))
                    return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serie, currentSeason, userChapters);
    }
}
